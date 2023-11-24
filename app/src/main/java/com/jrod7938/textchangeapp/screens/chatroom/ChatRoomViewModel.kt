/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2023, Jrod7938, Khang-ALe, jesma14, Holesum
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.jrod7938.textchangeapp.screens.chatroom

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.jrod7938.textchangeapp.model.MBook
import com.jrod7938.textchangeapp.model.MChatRoom
import com.jrod7938.textchangeapp.model.MUser
import com.jrod7938.textchangeapp.model.Message
import com.jrod7938.textchangeapp.screens.account.AccountScreenViewModel
import com.jrod7938.textchangeapp.screens.details.BookInfoScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Date

/**
 */
class ChatRoomViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _chatRoom = MutableLiveData<MChatRoom>(null)
    val chatRoom: LiveData<MChatRoom> = _chatRoom

    private val _chatRoomMessages = MutableLiveData<List<Message>>()
    val chatRoomMessages: LiveData<List<Message>> = _chatRoomMessages

    private val _user = MutableLiveData<MUser>(null)
    val user : LiveData<MUser> = _user

    private val _chatRoomMemberships = MutableLiveData<List<MChatRoom>>()
    val chatRoomMemberships = _chatRoomMemberships

    private val accountVM = AccountScreenViewModel()
    private val bookInfoVM = BookInfoScreenViewModel()

    fun loadUser() {
        _loading.value = true
        viewModelScope.launch {
            _user.value = accountVM.getUserInfo()
        }

    }


    private fun createChatRoom(
        senderId: String,
        recipientId: String,
        bookId: String,
    ){
        Log.d("Chatroom", "createChatRoom function called")
        _loading.value = true

        val chatRoomRealId = "${senderId}-${recipientId}-${bookId}"


        val chatRoom =
            MChatRoom(
                // chatroom id created with both participant id's and the book in question
                chatRoomId = chatRoomRealId,
                chatRoomName = "Untitled Conversation",
                messages = mutableListOf<Message>(),
                buyerId = senderId,
                sellerId = recipientId,
                bookId = bookId,
            )

        db.collection("chatRooms")
            .document(chatRoomRealId)
            .set(chatRoom.toMap().toMap())
            .addOnSuccessListener{
                try {
                    _chatRoom.value = chatRoom
                } catch(e: Exception){
                    _errorMessage.value = "An error occured: ${e.message}"
                }
                Log.d("Firebase", "chatRoom created successfully") //* TODO
            }.addOnFailureListener {
                Log.d("Firebase", "chatRoom creation failed") // * TODO
            }

    }

    fun loadChatRoom(
        senderId: String,
        recipientId: String,
        bookId: String,
    ) {
        Log.d("Chatroom", "loadChatRoom function called")
        _loading.postValue(true)
        viewModelScope.launch{
            db.collection("chatRooms")
                .whereEqualTo("chatRoomId", "${senderId}-${recipientId}-${bookId}")
                .get()
                .addOnSuccessListener {
                    if (it.documents.isEmpty()) {
                        Log.d("Chatroom", "List is empty")
                        _errorMessage.value =
                            "No chatroom found for specified parties. Creating new..."
                        createChatRoom(senderId = senderId, recipientId = recipientId, bookId = bookId)
                    } else {
                        Log.d("Chatroom", "List is not empty")
                        _chatRoom.value = MChatRoom.fromDocument(it.documents[0])
                        updateChatRoom()
                    }
                }.addOnFailureListener {
                    _errorMessage.value = "Error searching for chatrooms"
                }.await()
            _loading.postValue(false)
        }
    }


    fun writeMessage(
        chatRoomId: String,
        messageText: String,
        senderId: String,
        recipientId: String,
        timeStamp: Date,
    ) {

        val messageReference = db.collection("chatRooms").document(chatRoomId)
        Log.d("Chatroom", chatRoomId)

        val messageBody =
            Message(
                messageText = messageText,
                senderId = senderId,
                recipientId = recipientId,
                timeStamp = timeStamp
            )
        messageReference.update("messages", FieldValue.arrayUnion(messageBody))
            .addOnSuccessListener {
                updateChatRoom()
                Log.d("Chatroom", "Message sent")
            }.addOnFailureListener{
                _errorMessage.value = "There was an error sending your message"
            }


    }

    private fun updateChatRoom(){
        _loading.value = true
        val  chatRoomId = _chatRoom.value?.chatRoomId
        db.collection("chatRooms")
            .whereEqualTo("chatRoomId", chatRoomId)
            .get()
            .addOnSuccessListener{
                Log.d("Firebase", "Chat Room successfully updated")
            }.addOnFailureListener {
                Log.d("Firebase", "Chat Room could not update due to error")
            }
        viewModelScope.launch {
            if (chatRoomId != null) {
                _chatRoomMessages.value = retrieveMessages(chatRoomId)
                _chatRoom.value?.messages = _chatRoomMessages.value!!
            }
        }
        _loading.value = false
    }


    private suspend fun retrieveMessages(chatRoomId: String): List<Message> = withContext(Dispatchers.IO)
    {
        _loading.postValue(true)
        val messages = mutableListOf<Message>()
        try {
            val document = db.collection("chatRooms").document(chatRoomId).get().await()
            val chatRoomMessages = document.get("messages") as ArrayList<HashMap<Any, Any>>
            for(message in chatRoomMessages){
                try {
                    val newMessage = Message.fromMap(message)
                    messages.add(newMessage)
                    Log.d("Chatroom", "Message added")
                } catch(e: Exception){
                    Log.e("Chatroom", "Error fetching message")
                    _errorMessage.emit(e.message)
                }
            }
        } catch (e: Exception){
            _errorMessage.emit(e.message)
            Log.d("Chatroom", "Error fetching message log")
        }
        _loading.postValue(false)
        return@withContext messages
    }


    fun getChatRooms() {
//        _chatRoomMemberships.value = _chatRoomMemberships.value
//            ?.plus(getRoomByBuyerId())
//            ?.plus(getRoomBySellerId())
        getRoomByBuyerId()
    }

    private fun getRoomBySellerId(): List<MChatRoom>{
        var finalList : List<MChatRoom> = emptyList()
        val userId = _user.value?.userId
        viewModelScope.launch {
            _loading.postValue(true)
            db.collection("chatRooms")
                .whereEqualTo("sellerId", userId)
                .get()
                .addOnSuccessListener {
                    val chatList = it.map { document ->
                        MChatRoom.fromDocument(document)
                    }
                    Log.d("Chatroom", "$chatList")
                    finalList = chatList
                }.addOnFailureListener { exception ->
                    _errorMessage.value = exception.message
                }.await()

            _loading.postValue(false)

        }

        return finalList
    }

    private fun getRoomByBuyerId(): List<MChatRoom>{
        var finalList : List<MChatRoom> = emptyList()
        val userId = _user.value?.userId
        Log.d("Chatroom", "THE USER ID IS $userId")
        viewModelScope.launch {
            _loading.postValue(true)
            db.collection("chatRooms")
                .whereEqualTo("buyerId", userId)
                .get()
                .addOnSuccessListener {
                    val chatList = it.map { document ->
                        MChatRoom.fromDocument(document)
                    }
                    Log.d("Chatroom", "$chatList")
                    finalList = chatList
                }.addOnFailureListener { exception ->
                    Log.d("Chatroom", "FAILED to retrieve list")
                    _errorMessage.value = exception.message
                }.await()

            _loading.postValue(false)

        }

        return finalList

    }
}


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
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.jrod7938.textchangeapp.model.MChatRoom
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.jrod7938.textchangeapp.components.ChatRoomView
import com.jrod7938.textchangeapp.model.MBook
import com.jrod7938.textchangeapp.screens.details.BookInfoScreenViewModel
import kotlinx.coroutines.launch


@Composable
fun ChatRoomScreen(
    navController: NavHostController,
    viewModel: ChatRoomViewModel = viewModel(),
    bookId: String,
    senderId: String,
    recipientId: String,
){
    val chatRoom by viewModel.chatRoom.observeAsState(initial = null)
    val chatRoomMessages by viewModel.chatRoomMessages.observeAsState(initial = emptyList())
    val loading by viewModel.loading.observeAsState(initial = false)
    val currentUser by viewModel.user.observeAsState(initial = null)

    LaunchedEffect(key1 = true){
        launch {
            viewModel.loadUser()
        }
        launch {
            viewModel.loadChatRoom(
                senderId = senderId,
                recipientId = recipientId,
                bookId = bookId
            )
        }
    }

    if(loading) CircularProgressIndicator()
    if(!loading && currentUser != null && chatRoom != null){
        Log.d("Chatroom", "HEY, ${currentUser?.userId}")
        currentUser?.let { user ->
            chatRoom?.let { chatRoom ->
                ChatRoomView(
                    currentUser = user,
                    senderId = senderId,
                    recipientId = recipientId,
                    chatRoom = chatRoom,
                    chatRoomMessages = chatRoomMessages,
                    viewModel = viewModel
                )
            }
        }
    }

}
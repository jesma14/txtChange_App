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
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jrod7938.textchangeapp.navigation.AppScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConversationsScreen(
    navController: NavHostController,
    viewModel: ChatRoomViewModel = viewModel()
) {
    val loading by viewModel.loading.observeAsState(initial = false)
    val currentUser by viewModel.user.observeAsState(initial = null)
    val chatRoomMemberships by viewModel.chatRoomMemberships.observeAsState(initial = emptyList())

    val key1 by remember { mutableStateOf(currentUser == null) }

    LaunchedEffect(key1 = true){
        viewModel.loadUser()
        Log.d("Chatroom", "$currentUser")
    }

    LaunchedEffect(key1){
        Log.d("Chatroom", "LOOK $currentUser")
        viewModel.getChatRooms()
    }


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ){
        stickyHeader {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ){}
            Text(
                text = "My Conversations",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.background,
                fontSize = 15.sp
            )
        }

        item {
            if(loading) CircularProgressIndicator()
        }


        if(!loading && chatRoomMemberships != null && currentUser != null ) {
            Log.d("Chatroom", "${chatRoomMemberships.size}")
            currentUser?.let {
                Log.d("Chatroom", it.userId)
                chatRoomMemberships?.let { crm ->
                    crm.forEach { chatRoom ->
                        item {
                            Row { Text(
                                text = "HI ${chatRoom.bookId}",
                                modifier = Modifier.clickable {
                                    navController.navigate("${AppScreens.ChatRoomScreen.name}/${chatRoom.buyerId}-${chatRoom.sellerId}-${chatRoom.bookId}")
                                }) }
                        }
                    }
                }
            }
        }
    }

}
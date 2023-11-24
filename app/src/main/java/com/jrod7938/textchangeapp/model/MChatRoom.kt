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

package com.jrod7938.textchangeapp.model

import com.google.firebase.firestore.DocumentSnapshot

data class MChatRoom(
    var chatRoomId: String,
    var chatRoomName: String,
    var messages: List<Message>,
    var buyerId: String,
    var sellerId: String,
    var bookId: String,
) {
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf<String, Any>(
            "chatRoomId" to this.chatRoomId,
            "chatRoomName" to this.chatRoomName,
            "messages" to this.messages,
            "buyerId" to this.buyerId,
            "sellerId" to this.sellerId,
            "bookId" to this.bookId,
        )
    }

    companion object {
        fun fromDocument(document: DocumentSnapshot): MChatRoom {
            return MChatRoom(
                chatRoomId = document.getString("chatRoomId") ?: "",
                chatRoomName = document.getString("chatRoomName") ?: "",
                messages = document.get("messages") as List<Message>,
                buyerId = document.getString("buyerId") ?: "",
                sellerId = document.getString("sellerId") ?: "",
                bookId = document.getString("bookId") ?: "",

            )
        }
    }
}
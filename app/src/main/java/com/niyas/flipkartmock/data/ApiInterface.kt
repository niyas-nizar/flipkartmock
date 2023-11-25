package com.niyas.flipkartmock.data

import com.niyas.flipkartmock.data.models.response.ChatListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("codebuds-fk/chat/chats")
    suspend fun getChatList()
            : Response<ChatListResponse>

}
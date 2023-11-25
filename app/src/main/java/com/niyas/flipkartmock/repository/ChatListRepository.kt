package com.niyas.flipkartmock.repository

import com.niyas.flipkartmock.data.ApiInterface
import com.niyas.flipkartmock.data.NetworkResult
import com.niyas.flipkartmock.data.models.response.ChatListResponse
import com.niyas.flipkartmock.data.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@OptIn(KoinApiExtension::class)
class ChatListRepository constructor(private val apiInterface: ApiInterface) : KoinComponent {


    suspend fun getChatDetails() =
        withContext(Dispatchers.IO) {
            safeApiCall(
                call = { fetchChatDetails() },
                errorMessage = "Failed to get chat details, please try again."
            )
        }

    private suspend fun fetchChatDetails():
            NetworkResult<ChatListResponse> {
        val response = apiInterface.getChatList()
        if (response.isSuccessful) {
            return NetworkResult.Success(response)
        }
        return NetworkResult.Failure.Error(response)

    }
}
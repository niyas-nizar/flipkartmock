package com.niyas.flipkartmock.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niyas.flipkartmock.data.NetworkResult
import com.niyas.flipkartmock.data.UIModel
import com.niyas.flipkartmock.data.models.response.ChatListResponse
import com.niyas.flipkartmock.repository.ChatListRepository
import kotlinx.coroutines.launch

class ChatDetailsViewModel(
    private val chatListRepository: ChatListRepository
) : ViewModel() {

    fun getChatDetails(): MutableLiveData<UIModel<ChatListResponse>> {
        val response = MutableLiveData<UIModel<ChatListResponse>>()

        viewModelScope.launch {
            response.postValue(UIModel.loading(null))
            when (val result = chatListRepository.getChatDetails()) {
                is NetworkResult.Failure.Error -> {
                    response.postValue(
                        UIModel.error(
                            result.errorData?.message
                                ?: "Unable to get chat details. Please try again",
                            null
                        )
                    )
                }

                is NetworkResult.Failure.Exception -> response.postValue(
                    UIModel.error(
                        result.message ?: "Unable to get chat details. Please try again",
                        null
                    )
                )

                is NetworkResult.Success -> {
                    response.postValue(UIModel.success(result.data, null))
                }
            }
        }
        return response
    }

}
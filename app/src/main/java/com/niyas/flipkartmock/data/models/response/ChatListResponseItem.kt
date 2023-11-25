package com.niyas.flipkartmock.data.models.response

data class ChatListResponseItem(
    val id: Int,
    val imageURL: String,
    val latestMessageTimestamp: Long,
    var messageList: List<Message>,
    val orderId: String,
    val title: String
)
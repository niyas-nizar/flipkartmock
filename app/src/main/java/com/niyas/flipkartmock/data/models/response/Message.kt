package com.niyas.flipkartmock.data.models.response

data class Message(
    val message: String,
    val messageId: String,
    val messageType: String,
    val options: List<Option> ?= null,
    val sender: String,
    val timestamp: Long
)
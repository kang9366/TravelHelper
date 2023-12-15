package com.example.travelhelper.data.remote.model

data class ChatGptRequest(
    val model: String = "gpt-3.5-turbo-0301",
    val messages: List<RequestMessage>,
    val temperature: Double = 0.3,
    val max_tokens: Int = 200
)

data class RequestMessage(
    val role: String = "user",
    val content: String
)
package com.example.network.retrofit.model

data class Origin(
    val name: String? = null,
    val url: String? = null,
    val additionalProperties: Map<String, Any> = mapOf()
)


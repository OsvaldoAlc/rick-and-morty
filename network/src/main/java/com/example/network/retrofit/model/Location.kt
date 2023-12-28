package com.example.network.retrofit.model



data class Location(
    val name: String? = null,
    val url: String? = null,
    val additionalProperties: Map<String, Any> = mapOf()
)


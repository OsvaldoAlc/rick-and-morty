package com.example.network.retrofit.model

import kotlinx.serialization.Serializable

@Serializable

data class Location(
    val name: String? = null,
    val url: String? = null,
    val additionalProperties: Map<String, Any> = mapOf()
)


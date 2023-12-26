package com.example.network.retrofit.model

import kotlinx.serialization.Serializable


@Serializable
data class Info(
    val count: Int? = null,
    val pages: Int? = null,
    val next: String? = null,
    val prev: Any? = null,
    val additionalProperties: Map<String, Any> = LinkedHashMap()
)


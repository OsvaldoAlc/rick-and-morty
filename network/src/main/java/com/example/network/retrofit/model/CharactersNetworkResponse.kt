package com.example.network.retrofit.model

import kotlinx.serialization.Serializable


@Serializable
data class CharactersNetworkResponse(
    val info: Info? = null,
    val results: List<Result>? = null,
    val additionalProperties: Map<String, Any> = LinkedHashMap()
)


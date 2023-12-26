package com.example.network.retrofit.model

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val origin: Origin? = null,
    val location: Location? = null,
    val image: String? = null,
    val episode: List<String>? = null,
    val url: String? = null,
    val created: String? = null,
    val additionalProperties: Map<String, Any> = mapOf()
)

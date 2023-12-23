package com.example.network.retrofit.model

class CharactersNetworkResponse {
    var info: Info? = null
    var results: List<Result>? = null
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}

package com.example.network.retrofit.model

class Origin {
    var name: String? = null
    var url: String? = null
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}

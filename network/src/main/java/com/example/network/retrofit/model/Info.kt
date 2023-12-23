package com.example.network.retrofit.model

class Info {
    var count: Int? = null
    var pages: Int? = null
    var next: String? = null
    var prev: Any? = null
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}

package com.example.network.retrofit.model

class Result {
    var id: Int? = null
    var name: String? = null
    var status: String? = null
    var species: String? = null
    var type: String? = null
    var gender: String? = null
    var origin: Origin? = null
    var location: Location? = null
    var image: String? = null
    var episode: List<String>? = null
    var url: String? = null
    var created: String? = null
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}

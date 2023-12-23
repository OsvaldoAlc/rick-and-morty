package com.example.network.retrofit

import com.example.network.retrofit.model.CharactersNetworkResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Retrofit API declaration for NIA Network API
 */
private interface RetrofitRickApi {
    @GET(value = "character")
    suspend fun getCharacters(
    ): CharactersNetworkResponse
}

private const val NIA_BASE_URL = "https://rickandmortyapi.com/api/"

/**
 * Wrapper for data provided from the [NIA_BASE_URL]
 */
@Serializable
private data class NetworkResponse<T>(
    val data: T,
)

/**
 * [Retrofit] backed [NiaNetworkDataSource]
 */
@Singleton
class RetrofitNiaNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : NiaNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(NIA_BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory(MediaType.get("application/json; charset=utf-8")),
        )
        .build()
        .create(RetrofitRickApi::class.java)


    override suspend fun getTopics(): CharactersNetworkResponse =
        networkApi.getCharacters()

}

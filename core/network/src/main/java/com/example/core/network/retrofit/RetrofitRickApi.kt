package com.example.network.retrofit

import com.example.network.retrofit.model.CharactersNetworkResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
 * [Retrofit] backed [RmNetworkDataSource]
 */

@Singleton
class RetrofitRmNetwork @Inject constructor(
    client: OkHttpClient
) : RmNetworkDataSource {



    private val networkApi = Retrofit.Builder()
        .baseUrl(NIA_BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(),
        )
        .client(client)
        .build()
        .create(RetrofitRickApi::class.java)


    override suspend fun getTopics(): CharactersNetworkResponse =
        networkApi.getCharacters()
}

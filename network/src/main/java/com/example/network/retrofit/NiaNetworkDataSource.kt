package com.example.network.retrofit

import com.example.network.retrofit.model.CharactersNetworkResponse

interface NiaNetworkDataSource {
    suspend fun getTopics(): CharactersNetworkResponse
}
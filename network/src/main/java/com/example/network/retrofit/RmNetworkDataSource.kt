package com.example.network.retrofit

import com.example.network.retrofit.model.CharactersNetworkResponse

interface RmNetworkDataSource {
    suspend fun getTopics(): CharactersNetworkResponse
}
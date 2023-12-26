package com.example.data.repository

import com.example.data.model.asCharacter
import com.example.model.RMCharacter
import com.example.network.retrofit.RmNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val network: RmNetworkDataSource,
) : CharacterRepository {
    override suspend fun getCharacters(): Flow<List<RMCharacter>> = flow {
        val result = network.getTopics().results?.map { it.asCharacter() }
        emit(
            result ?: emptyList()
        )
    }
}
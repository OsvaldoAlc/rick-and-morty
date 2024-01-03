package com.example.data.repository

import com.example.data.model.asCharacter
import com.example.model.RMCharacter
import com.example.network.di.Dispatcher
import com.example.network.di.NiaDispatchers
import com.example.network.retrofit.RmNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val network: RmNetworkDataSource,
    @Dispatcher(NiaDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : CharacterRepository {
    override fun getCharacters(): Flow<List<RMCharacter>> = flow {
        val result = withContext(ioDispatcher) {
            network.getTopics().results
        }
            ?.map {
                it.asCharacter()
            }
        emit(
            result ?: emptyList()
        )
    }
}
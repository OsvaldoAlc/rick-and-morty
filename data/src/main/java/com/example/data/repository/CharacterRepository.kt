package com.example.data.repository

import com.example.model.RMCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<List<RMCharacter>>
}
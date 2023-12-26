package com.example.domain

import com.example.data.repository.CharacterRepository
import java.util.concurrent.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke() =
        repository.getCharacters()
}
package com.example.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.model.Result
import com.example.core.common.model.asResult
import com.example.domain.GetCharactersUseCase
import com.example.model.RMCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetCharactersUseCase,
) : ViewModel() {


    val uiState: StateFlow<TopicUiState> = useCase()
        .asResult()
        .map {
            when (it) {
                is Result.Success -> {
                    TopicUiState.Success(it.data)
                }

                is Result.Loading -> {
                    TopicUiState.Loading
                }

                is Result.Error -> {
                    TopicUiState.Error
                }
            }
        }
        .catch {
            println("Erorr")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TopicUiState.Loading
        )

    private val _currentCharacter = MutableStateFlow<RMCharacter>(RMCharacter("", ""))
    val currentCharacter = _currentCharacter.asStateFlow()

    fun updateCharacter(rmCharacter: RMCharacter) {
        _currentCharacter.value = rmCharacter
    }
}

sealed interface TopicUiState {
    data class Success(val characters: List<RMCharacter>) : TopicUiState
    data object Error : TopicUiState
    data object Loading : TopicUiState
}
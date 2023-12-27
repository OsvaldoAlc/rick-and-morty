package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.model.Result
import com.example.common.model.asResult
import com.example.domain.GetCharactersUseCase
import com.example.model.RMCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetCharactersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TopicUiState>(TopicUiState.Loading)
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<TopicUiState> = _uiState

    fun getCharacters() {
        viewModelScope.launch {
            useCase().asResult()
                .map {
                    when(it) {
                        is Result.Success -> {
                            _uiState.value = TopicUiState.Success(it.data)
                        }
                        is Result.Loading -> TopicUiState.Loading
                        is Result.Error -> TopicUiState.Error
                    }
                }

        }
    }
}

sealed interface TopicUiState {
    data class Success(val characters: List<RMCharacter>) : TopicUiState
    data object Error : TopicUiState
    data object Loading : TopicUiState
}
package com.example.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.model.asResult
import com.example.core.common.model.Result
import com.example.domain.GetCharactersUseCase
import com.example.model.RMCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetCharactersUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TopicUiState>(TopicUiState.Loading)
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<TopicUiState> = _uiState.asStateFlow()

    private val _currentCharacter = MutableStateFlow<RMCharacter>(RMCharacter("",""))
    val currentCharacter = _currentCharacter.asStateFlow()

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
                }.collect()

        }
    }

    fun updateCharacter(rmCharacter: RMCharacter) {
        _currentCharacter.value = rmCharacter
    }
}

sealed interface TopicUiState {
    data class Success(val characters: List<RMCharacter>) : TopicUiState
    data object Error : TopicUiState
    data object Loading : TopicUiState
}
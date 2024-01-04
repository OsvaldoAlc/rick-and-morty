package com.example.feature.home

import com.example.domain.GetCharactersUseCase
import com.example.model.RMCharacter
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var homeViewModel: HomeViewModel

    private val getCharactersUseCase = mockk<GetCharactersUseCase>()

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(
            getCharactersUseCase
        )
    }

    @Test
    fun `initial State is Loading`() = runTest {
        assertThat(homeViewModel.uiState.value).isEqualTo(TopicUiState.Loading)
    }

    @Test
    fun `State goes to success when usecase returns results`() = runTest {

        val myCharacters = listOf<RMCharacter>(
            RMCharacter("Rick", "Rick.jpg"),
            RMCharacter("Sanchex", "Sanchex.jpg")
        )

        every { getCharactersUseCase.invoke() } returns flowOf(myCharacters)

        homeViewModel.getCharacters()
        assertThat(homeViewModel.uiState.value).isEqualTo(TopicUiState.Success(myCharacters))
    }

    @Test
    fun `State goes to error when there is an Exception`() = runTest {
        every { getCharactersUseCase.invoke() } throws Exception("Error")

        homeViewModel.getCharacters()
        assertThat(homeViewModel.uiState.value).isEqualTo(TopicUiState.Error)
    }
}
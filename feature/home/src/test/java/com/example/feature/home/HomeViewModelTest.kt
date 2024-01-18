package com.example.feature.home

import com.example.data.repository.CharacterRepository
import com.example.domain.GetCharactersUseCase
import com.example.model.RMCharacter
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = com.example.core.testing.util.MainDispatcherRule()

    private lateinit var homeViewModel: HomeViewModel

    private val getCharactersUseCase = mockk<GetCharactersUseCase>()

    @Before
    fun setup() {

    }

    @Test
    fun `initial State is Loading`() = runTest {
        val myCharacters = listOf<RMCharacter>(
            RMCharacter("Rick", "Rick.jpg"),
            RMCharacter("Sanchex", "Sanchex.jpg")
        )

        every { getCharactersUseCase.invoke() } returns flowOf(myCharacters)
        homeViewModel = HomeViewModel(
            getCharactersUseCase
        )
        assertThat(homeViewModel.uiState.value).isEqualTo(TopicUiState.Loading)
    }

    @Test
    fun `State goes to success when usecase returns results`() = runTest {

        val myCharacters = listOf<RMCharacter>(
            RMCharacter("Rick", "Rick.jpg"),
            RMCharacter("Sanchex", "Sanchex.jpg")
        )

        every { getCharactersUseCase.invoke() } returns flowOf(myCharacters)


        homeViewModel = HomeViewModel(
            getCharactersUseCase
        )

        val collectJob1 =
            launch(UnconfinedTestDispatcher()) { homeViewModel.uiState.collect() }

        assertThat(homeViewModel.uiState.value).isEqualTo(TopicUiState.Success(myCharacters))

        collectJob1.cancel()
    }

    @Test
    fun `State goes to error when there is an Exception`() = runTest {

        homeViewModel = HomeViewModel(
            GetCharactersUseCase(FakeR())
        )

        val collectJob1 =
            launch(UnconfinedTestDispatcher()) {
                homeViewModel.uiState.collect()
            }


        assertThat(homeViewModel.uiState.value).isEqualTo(TopicUiState.Error)


        collectJob1.cancel()
    }
}


class FakeR: CharacterRepository {
    override fun getCharacters(): Flow<List<RMCharacter>> {
        throw Exception("ay no" )
    }
}
package com.sumeyra.feature

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.sumeyra.domain.usecase.GetMovieDetailUseCase
import com.sumeyra.feature.movieapp.detail.DetailContract
import com.sumeyra.feature.movieapp.detail.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.jvm.java

/**
 * Created on 12.01.2026
 * @author Sümeyra Özuğur
 */

@ExperimentalCoroutinesApi
internal class DetailViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getMovieDetailUseCase = mock(GetMovieDetailUseCase::class.java)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should call load and update state with movie detail`() = runTest {
        val testId = 101
        val mockDetail = TestDataFactory.createMovieDetailedModel(id =  101)

        `when`(getMovieDetailUseCase(testId)).thenReturn(mockDetail)

        viewModel = DetailViewModel(getMovieDetailUseCase, SavedStateHandle(mapOf("movieId" to testId)))

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(testId, state.movie?.id)
            assertFalse(state.isLoading)
        }
    }

    @Test
    fun `when OnBackClicked event is received, should emit NavigateBack effect`() = runTest {
        val testId = 101
        `when`(getMovieDetailUseCase(testId)).thenReturn(TestDataFactory.createMovieDetailedModel(id = testId))

        viewModel = DetailViewModel(getMovieDetailUseCase, SavedStateHandle(mapOf("movieId" to testId)))

        viewModel.effect.test {
            viewModel.handleEvent(DetailContract.Event.OnBackClicked)

            val effect = awaitItem()
            assertTrue(effect is DetailContract.Effect.NavigateBack)
        }
    }

    @Test
    fun `when OnDiscoverPopularClicked event is received, should show message sumeyra`() = runTest {
        val testId = 101
        `when`(getMovieDetailUseCase(testId)).thenReturn(TestDataFactory.createMovieDetailedModel(id = testId))

        viewModel = DetailViewModel(getMovieDetailUseCase, SavedStateHandle(mapOf("movieId" to testId)))

        viewModel.effect.test {
            viewModel.handleEvent(DetailContract.Event.OnDiscoverPopularClicked)

            val effect = awaitItem()
            assertTrue(effect is DetailContract.Effect.ShowMessage)
            assertEquals("sumeyra", (effect as DetailContract.Effect.ShowMessage).message)
        }
    }

    @Test
    fun `when OnRetry is received, it should reload the movie`() = runTest {
        val testId = 101
        val mockMovieDetail = TestDataFactory.createMovieDetailedModel(id = testId)

        `when`(getMovieDetailUseCase(testId)).thenReturn(mockMovieDetail)

        viewModel = DetailViewModel(getMovieDetailUseCase, SavedStateHandle(mapOf("movieId" to testId)))

        viewModel.handleEvent(DetailContract.Event.OnRetry)

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(mockMovieDetail, state.movie)
            assertEquals(testId, state.movie?.id)
        }
    }
}
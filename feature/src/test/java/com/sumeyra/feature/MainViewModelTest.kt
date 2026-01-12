package com.sumeyra.feature

/**
 * Created on 12.01.2026
 * @author Sümeyra Özuğur
 */
import app.cash.turbine.test
import com.sumeyra.domain.usecase.GetNowPlayingMoviesUseCase
import com.sumeyra.domain.usecase.GetUpcomingMoviesUseCase
import com.sumeyra.feature.movieapp.main.MainContract
import com.sumeyra.feature.movieapp.main.MainViewModel
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

@ExperimentalCoroutinesApi
internal class MainViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
    private lateinit var  getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase

    private lateinit var viewModel: MainViewModel


    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher)

        getUpcomingMoviesUseCase = mock(GetUpcomingMoviesUseCase::class.java)
        getNowPlayingMoviesUseCase = mock(GetNowPlayingMoviesUseCase::class.java)


    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getUpComingMovies is called, sliderMovies should be updated`() = runTest {

        val mockUpcoming = TestDataFactory.createMovieListModel(page = 1)
        val mockNowPlaying = TestDataFactory.createMovieListModel(page = 1)

        `when`(getUpcomingMoviesUseCase(1)).thenReturn(mockUpcoming)
        `when`(getNowPlayingMoviesUseCase(1)).thenReturn(mockNowPlaying)

        viewModel = MainViewModel(getUpcomingMoviesUseCase, getNowPlayingMoviesUseCase)

        viewModel.getUpComingMovies(1)

        viewModel.uiState.test {
            val state = awaitItem()

            assertEquals(mockUpcoming.movieList.take(5), state.sliderMovies)
        }
    }

    @Test
    fun `when getNowPlayingMovies is called, nowPlayingMovieList should be updated`() = runTest {
        val mockNowPlaying = TestDataFactory.createMovieListModel(page = 1, totalPage = 10)
        val mockUpcoming = TestDataFactory.createMovieListModel()

        `when`(getNowPlayingMoviesUseCase(1)).thenReturn(mockNowPlaying)
        `when`(getUpcomingMoviesUseCase(1)).thenReturn(mockUpcoming)

        viewModel = MainViewModel(getUpcomingMoviesUseCase, getNowPlayingMoviesUseCase)

        viewModel.getNowPlayingMovies(1)

        viewModel.uiState.test {
            val state = awaitItem()

            assertEquals(mockNowPlaying.movieList, state.nowPlayingMovieList)
            assertEquals(1, state.page)
            assertEquals(10, state.pageCount)
            assertFalse(state.isRefreshing)
            assertFalse(state.isLoadingMore)
        }
    }

    @Test
    fun `when OnLoadPage event is received, should call getNowPlayingMovies with next page`() = runTest {
        val nextPageResponse = TestDataFactory.createMovieListModel(page = 2, totalPage = 5)

        `when`(getNowPlayingMoviesUseCase(1)).thenReturn(TestDataFactory.createMovieListModel(page = 1))
        `when`(getNowPlayingMoviesUseCase(2)).thenReturn(nextPageResponse)
        `when`(getUpcomingMoviesUseCase(1)).thenReturn(TestDataFactory.createMovieListModel())

        viewModel = MainViewModel(getUpcomingMoviesUseCase, getNowPlayingMoviesUseCase)

        viewModel.handleEvent(MainContract.Event.OnLoadPage)

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(2, state.page)
        }
    }

    @Test
    fun `when OnMovieClicked event is received, should emit NavigateToDetail effect`() = runTest {

        `when`(getNowPlayingMoviesUseCase(1)).thenReturn(TestDataFactory.createMovieListModel())
        `when`(getUpcomingMoviesUseCase(1)).thenReturn(TestDataFactory.createMovieListModel())

        viewModel = MainViewModel(getUpcomingMoviesUseCase, getNowPlayingMoviesUseCase)

        viewModel.effect.test {

            viewModel.handleEvent(MainContract.Event.OnMovieClicked("2"))

            val effect = awaitItem()
            assertTrue(effect is MainContract.Effect.NavigateToDetail)
            assertEquals("2", (effect as MainContract.Effect.NavigateToDetail).id)
        }
    }

    @Test
    fun `when OnRefresh event is received, should fetch movies for page 1 and reset refreshing state`() = runTest {
        val mockResponse = TestDataFactory.createMovieListModel(page = 1)

        `when`(getNowPlayingMoviesUseCase(1)).thenReturn(mockResponse)
        `when`(getUpcomingMoviesUseCase(1)).thenReturn(TestDataFactory.createMovieListModel())

        viewModel = MainViewModel(getUpcomingMoviesUseCase, getNowPlayingMoviesUseCase)

        viewModel.handleEvent(MainContract.Event.OnRefresh)

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(mockResponse.movieList, state.nowPlayingMovieList)
            assertEquals(1, state.page)
            assertFalse(state.isRefreshing)
        }
    }
}
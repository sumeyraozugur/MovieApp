package com.sumeyra.feature.movieapp.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sumeyra.core.ui.theme.MovieAppTheme

/**
 * Created on 8.01.2026
 * @author Sümeyra Özuğur
 */

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    state: MainContract.State,
    event: (MainContract.Event) -> Unit,
    onMovieClick: (String) -> Unit,
    effect: MainContract.Effect?
) {

    LaunchedEffect(effect) {

        when (effect) {
            is MainContract.Effect.NavigateToDetail -> onMovieClick(effect.id)
            else -> {}
        }
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = { event(MainContract.Event.OnRefresh) }
    )

    val lazyListState = rememberLazyListState()

    val isAtEndOfList by remember {
        derivedStateOf {
            val layoutInfo = lazyListState.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            if (layoutInfo.totalItemsCount == 0) return@derivedStateOf false

            val lastVisibleItem = visibleItemsInfo.lastOrNull() ?: return@derivedStateOf false
            val viewportEndOffset = layoutInfo.viewportEndOffset
            (lastVisibleItem.index >= layoutInfo.totalItemsCount - 3) &&
                    (lastVisibleItem.offset + lastVisibleItem.size <= viewportEndOffset)
        }
    }

    LaunchedEffect(isAtEndOfList) {
        if (isAtEndOfList && state.canLoadMore && !state.isLoadingMore && !state.isRefreshing) {
            event(MainContract.Event.OnLoadPage)
        }
    }

    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(
            state = lazyListState,
            contentPadding = PaddingValues(bottom = MovieAppTheme.spacing.md),
            modifier = Modifier.fillMaxSize()
        ) {
            item(key = "upcoming_slider") {
               MovieUpcomingSlider(movies = state.sliderMovies,
                   onMovieClick = { clicked->
                       event(MainContract.Event.OnMovieClicked(clicked.toString()))

                   })
           }


            if (state.nowPlayingMovieList.isNotEmpty()) {
                itemsIndexed(state.nowPlayingMovieList) { _, movie ->
                    MovieListItem(
                        movie = movie,
                        onClick = { clicked ->
                            event(MainContract.Event.OnMovieClicked(clicked))

                        }
                    )
                }
            }

            if (state.isLoadingMore) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MovieAppTheme.spacing.lg),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

        if (state.nowPlayingMovieList.isEmpty() && !state.isRefreshing) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        PullRefreshIndicator(
            refreshing = state.isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun MainScreen_Preview(
    @PreviewParameter( MainPreviewProvider::class) uiState: MainContract.State,
) {
    MainScreen(
        state = uiState,
        event = { _ -> },
        onMovieClick = {},
        effect = null
    )
}
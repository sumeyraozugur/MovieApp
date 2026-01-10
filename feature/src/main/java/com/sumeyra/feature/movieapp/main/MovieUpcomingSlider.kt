package com.sumeyra.feature.movieapp.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sumeyra.core.ui.theme.MovieAppTheme
import com.sumeyra.domain.model.MovieModel

@Composable
fun MovieUpcomingSlider(
    modifier: Modifier = Modifier,
    movies: List<MovieModel>,
    onMovieClick: (Int) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { movies.size })

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.66f)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val movie = movies[page]
            SliderItem(
                movie = movie,
                onClick = { movie.id?.let { onMovieClick(it) } }
            )
        }

        PagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = MovieAppTheme.spacing.sm)
        )
    }
}


@Preview(showBackground = true, name = "Movie List Item")
@Composable
fun MovieUpcomingSlider_Preview() {
  val mockMovies = listOf(
       MovieModel(
          id = 1,
          title = "Girl Green Hood",
          titleWithDate = "Girl Green Hood (2014)",
          backdropPath = "",
          releaseDate = "2014-10-10",
          overview = "In the enchanted realm of words, a description unfolds..."
      )
  )
    MovieUpcomingSlider(
        movies = mockMovies,
        onMovieClick = {}
    )
}
package com.sumeyra.feature.movieapp.main

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sumeyra.domain.model.MovieModel

class MainPreviewProvider : PreviewParameterProvider<MainContract.State> {
    override val values: Sequence<MainContract.State>
        get() = sequenceOf(
            MainContract.State(
                upcomingMovieList = listOf(
                    MovieModel(
                        1311031,
                        "Demon Slayer…",
                        "…(2025)",
                        "https://image.tmdb.org/t/p/w500/ZtcGMc204JsNqfjS9lU6udRgpo.jpg",
                        "2025-07-18",
                        "The Demon Slayer…"
                    ),
                    MovieModel(
                        1311032,
                        "Marry Kom…",
                        "Marry Kom(2025)",
                        "https://image.tmdb.org/t/p/w500/ZtcGMc204JsNqfjS9lU6udRgpo.jpg",
                        "2025-05-12",
                        "The histroy of Marry Kom…"
                    )

                ),

                nowPlayingMovieList = listOf(
                    MovieModel(
                        1311031,
                        "Demon Slayer…",
                        "…(2025)",
                        "https://image.tmdb.org/t/p/w500/ZtcGMc204JsNqfjS9lU6udRgpo.jpg",
                        "2025-07-18",
                        "The Demon Slayer…"
                    ),
                    MovieModel(
                        1311032,
                        "Marry Kom…",
                        "Marry Kom(2025)",
                        "https://image.tmdb.org/t/p/w500/ZtcGMc204JsNqfjS9lU6udRgpo.jpg",
                        "2025-05-12",
                        "The histroy of Marry Kom…"
                    )

                ),
            ),
        )
}

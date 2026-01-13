package com.sumeyra.feature.movieapp.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sumeyra.core.ui.theme.MovieAppTheme

/**
 * Created on 9.01.2026
 * @author Sümeyra Özuğur
 */

@Composable
fun DetailScreen(
    state: DetailContract.State,
    event: (DetailContract.Event) -> Unit,
    onBack: () -> Unit,
    effect: DetailContract.Effect?,
) {
    val context = LocalContext.current

    LaunchedEffect(effect) {
        when (effect) {
            is DetailContract.Effect.NavigateBack -> onBack()

            is DetailContract.Effect.ShowMessage -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()

            null -> {}
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .semantics { testTagsAsResourceId = true }) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = MovieAppTheme.spacing.section72)
        ) {

            item {
                MovieHeaderImage(imageUrl = state.movie?.backdropPath) {
                    IconButton(
                        onClick = { event(DetailContract.Event.OnBackClicked) },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .statusBarsPadding()
                            .testTag("back_button")
                    ) {
                        Icon(
                            modifier = Modifier.size(MovieAppTheme.spacing.section36),
                            imageVector = MovieAppTheme.icons.arrowBack,
                            tint = MovieAppTheme.colors.background,
                            contentDescription = "Geri"
                        )
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .padding(MovieAppTheme.spacing.lg)
                        .fillMaxWidth()
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = MovieAppTheme.icons.star,
                            contentDescription = null,
                            tint = MovieAppTheme.colors.softRed,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = " ${state.movie?.rating}  •  ${state.movie?.releaseDate}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MovieAppTheme.colors.onBackground,
                        )
                    }

                    Spacer(modifier = Modifier.height(MovieAppTheme.spacing.md))

                    Text(
                        text = state.movie?.title ?: "",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.testTag("movie_detail_title"),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(MovieAppTheme.spacing.lg))

                    Text(
                        text = "Özet",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(MovieAppTheme.spacing.sm))
                    Text(
                        text = state.movie?.overview ?: "",
                        style = MaterialTheme.typography.bodyLarge,
                        lineHeight = 24.sp
                    )
                }
            }
        }


        DiscoverPopularButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding()
                .testTag("discover_button")
                .padding(horizontal = MovieAppTheme.spacing.lg),
            onClick = {
                event(DetailContract.Event.OnDiscoverPopularClicked)
            }
        )

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
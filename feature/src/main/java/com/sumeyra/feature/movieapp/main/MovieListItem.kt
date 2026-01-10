package com.sumeyra.feature.movieapp.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sumeyra.core.ui.theme.MovieAppTheme
import com.sumeyra.domain.model.MovieModel


@Composable
fun MovieListItem(
    movie: MovieModel,
    onClick: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { movie.id?.let { onClick(it.toString()) } }
            .padding(horizontal = MovieAppTheme.spacing.lg, vertical = MovieAppTheme.spacing.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = movie.backdropPath,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(MovieAppTheme.spacing.md))
                .size(MovieAppTheme.spacing.section120)
        )



        Spacer(modifier = Modifier.width(MovieAppTheme.spacing.md))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = movie.titleWithDate,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(MovieAppTheme.spacing.xs))
            Text(
                text = movie.overview,
                color = Color.Gray,
                fontSize = 14.sp,
                maxLines = 3
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListItem_Preview() {
    MovieListItem(
        movie = MovieModel(
            id = 42,
            title = "Moviee 2025",
            titleWithDate = "Moviee 2025",
            backdropPath = "https://image.tmdb.org/t/p/w500/b2.jpg",
            releaseDate = "2025-04-10",
            overview = "Kısa bir özet metni. Bu metin en fazla üç satırda ellipsis ile kırpılır."
        ),
        onClick = {}
    )
}
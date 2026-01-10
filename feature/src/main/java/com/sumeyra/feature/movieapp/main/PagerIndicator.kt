package com.sumeyra.feature.movieapp.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sumeyra.core.ui.theme.MovieAppTheme

@Composable
fun PagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.height(MovieAppTheme.spacing.xxl),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val isSelected = pagerState.currentPage == iteration
            Box(
                modifier = Modifier
                    .padding(MovieAppTheme.spacing.xs)
                    .size(if (isSelected) MovieAppTheme.spacing.sm10 else MovieAppTheme.spacing.sm)
                    .clip(CircleShape)
                    .background(if (isSelected) Color.White else Color.Gray.copy(alpha = 0.5f))
            )
        }
    }
}

@Preview(showBackground = true, name = "PagerIndicator")
@Composable
fun PageIndicator_Preview() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 5 }
    )
    PagerIndicator(
        pagerState = pagerState
    )
}
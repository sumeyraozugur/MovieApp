package com.sumeyra.feature.movieapp.detail

import android.R.attr.onClick
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sumeyra.core.ui.theme.MovieAppTheme

@Composable
fun DiscoverPopularButton(
    modifier: Modifier = Modifier,
    onClick: ()->Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(MovieAppTheme.spacing.section52),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MovieAppTheme.colors.yellow,
            contentColor = Color.Black
        )
    ) {
        Text(
            text = "Mesajı göster",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
fun DiscoverPopularButtonPreview(){
    DiscoverPopularButton(onClick={})
}
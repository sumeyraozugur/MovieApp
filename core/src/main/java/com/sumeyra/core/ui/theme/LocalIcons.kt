package com.sumeyra.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.sumeyra.core.R

val LocalIcons = staticCompositionLocalOf { MovieAppIcons() }

class MovieAppIcons {
    val arrowBack: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_arrow_back)

    val star: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_star)
}
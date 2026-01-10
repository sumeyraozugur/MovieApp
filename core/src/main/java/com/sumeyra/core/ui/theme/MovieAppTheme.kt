package com.sumeyra.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.foundation.isSystemInDarkTheme

object MovieAppTheme {

    val colors: MovieAppColor
        @Composable
        @ReadOnlyComposable
        get() = if (isSystemInDarkTheme()) LocalDarkColors.current else LocalLightColors.current

    val icons: MovieAppIcons
        @Composable
        @ReadOnlyComposable
        get() = LocalIcons.current


    val spacing: Spacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current
}

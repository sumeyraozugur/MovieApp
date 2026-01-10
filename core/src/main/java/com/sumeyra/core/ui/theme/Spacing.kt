package com.sumeyra.core.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
data class Spacing(
    val xs2:Dp = 2.dp,
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val sm10: Dp = 10.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val lg18: Dp = 18.dp,
    val xl: Dp = 20.dp,
    val xxl: Dp = 24.dp,
    val section: Dp = 32.dp,
    val section36: Dp = 36.dp,
    val section52: Dp = 52.dp,
    val section72: Dp = 72.dp,
    val section120: Dp = 120.dp,
    val section260: Dp = 260.dp
)

val LocalSpacing = staticCompositionLocalOf{ Spacing() }
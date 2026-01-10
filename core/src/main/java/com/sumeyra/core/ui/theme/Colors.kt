package com.sumeyra.core.ui.theme

import android.graphics.Color.blue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Created on 9.01.2026
 * @author Sümeyra Özuğur
 */

internal val LocalLightColors = staticCompositionLocalOf { lightColors() }
internal val LocalDarkColors = staticCompositionLocalOf { darkColors() }

internal fun darkColors(
    onBackground: Color = Color(0xFFfffbf3),
    background: Color = Color(0xFF969191),
    black: Color = Color(0xFF282828),
    lightBlue: Color = Color(0xFFBCD9FF).copy(alpha = 0.5f),
    softRed: Color = Color(0xFFFFD0BC).copy(alpha = 0.5f),
): MovieAppColor = MovieAppColor(
    background = background,
    onBackground = onBackground,
    black = black,
    yellow = lightBlue,
    softRed = softRed,
)

class MovieAppColor(
    background: Color,
    onBackground: Color,
    black: Color,
    yellow: Color,
    softRed: Color,
) {
    private var _background: Color by mutableStateOf(background)
    val background: Color = _background

    private var _onBackground: Color by mutableStateOf(onBackground)
    val onBackground: Color = _onBackground

    private var _black: Color by mutableStateOf(black)
    val black: Color = _black

    private var _yellow: Color by mutableStateOf(yellow)
    val yellow: Color = _yellow



    private var _softRed: Color by mutableStateOf(softRed)
    val softRed: Color = _softRed
}

internal fun lightColors(
    background: Color = Color(0xFFfffbf3),
    onBackground: Color = Color(0xFF111111),
    black: Color = Color(0xFF000000),
    yellow: Color = Color(0xFFBCD9FF),
    softRed: Color = Color(0xFFFFD0BC),
): MovieAppColor = MovieAppColor(
    background = background,
    onBackground = onBackground,
    black = black,
    yellow = yellow,
    softRed = softRed,
)
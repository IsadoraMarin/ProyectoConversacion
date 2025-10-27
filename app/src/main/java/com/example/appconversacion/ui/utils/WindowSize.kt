package com.example.appconversacion.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class WindowSize(val width: Dp) {
    object Compact : WindowSize(600.dp)
    object Medium : WindowSize(840.dp)
    object Expanded : WindowSize(1200.dp)
}

@Composable
fun rememberWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    return when {
        screenWidth < WindowSize.Compact.width -> WindowSize.Compact
        screenWidth < WindowSize.Medium.width -> WindowSize.Medium
        else -> WindowSize.Expanded
    }
}

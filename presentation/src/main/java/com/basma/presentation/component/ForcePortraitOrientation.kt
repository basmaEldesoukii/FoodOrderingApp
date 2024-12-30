package com.basma.presentation.component

import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun ForcePortraitOrientation() {
    val context = LocalContext.current
    DisposableEffect(key1 = Unit) {
        val activity =
            context as? androidx.activity.ComponentActivity ?: error("Context is not an Activity")
        val originalOrientation = activity.requestedOrientation

        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}
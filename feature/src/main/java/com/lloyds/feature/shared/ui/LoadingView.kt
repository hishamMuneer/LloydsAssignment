package com.lloyds.feature.shared.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Text(text = "Loading...", modifier = modifier.fillMaxSize())
}
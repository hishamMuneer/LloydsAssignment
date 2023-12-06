package com.lloyds.feature.shared.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorView(reason: String, modifier: Modifier = Modifier) {
    Text(text = "Unable to load champions, reason: $reason", modifier = modifier.fillMaxSize())
}
package com.lloyds.feature.shared.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun AppText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text = text,
        style = style,
        maxLines = maxLines,
        modifier = modifier
    )
}
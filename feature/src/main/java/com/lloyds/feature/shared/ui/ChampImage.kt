package com.lloyds.feature.shared.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.lloyds.feature.R

@Composable
fun ChampImage(
    image: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {

    // Coil library painter to load the image from URL
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = image)
            .placeholder(R.drawable.placeholder_img).build()
    )

    // Image with content scale and painter
    Image(
        painter = painter,
        contentDescription = null, // Content description for accessibility
        contentScale = contentScale,
        modifier = modifier.fillMaxSize(),
    )
}
package com.lloyds.feature.championdetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.lloyds.domain.model.Champion
import com.lloyds.feature.shared.ui.AppText
import com.lloyds.feature.shared.ui.ChampImage
import com.lloyds.feature.theme.GrayOverlay

@Composable
fun ChampionUI(champion: Champion) {

    // Box with content alignment and background color
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {

        // champ image
        ChampImage(image = champion.image)

        // Column for overlay text
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(GrayOverlay.copy(.7f)) // transparent
                .padding(
                    start = 4.dp, top = 16.dp, end = 4.dp, bottom = 16.dp
                )
                .align(Alignment.BottomCenter)
                .height(120.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                // Title text
                AppText(
                    text = champion.name,
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color.White),
                )

                // Description text
                AppText(
                    text = champion.title,
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.White),
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Lore text
                AppText(
                    text = champion.lore,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                )
            }
        }
    }
}
package com.lloyds.feature.champions.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.lloyds.domain.model.Champion
import com.lloyds.feature.shared.ui.AppText
import com.lloyds.feature.shared.ui.ChampImage
import com.lloyds.feature.theme.ChampTextWhite
import com.lloyds.feature.theme.GrayOverlay

@Composable
fun ChampionGridCardUI(champion: Champion, selectedItem: (String) -> Unit) {

    // Box with content alignment and background color
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { selectedItem(champion.id) } // click event
        .clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.surface)
        .padding(2.dp)
        .height(420.dp)) {

        // champ image
        ChampImage(image = champion.image)

        // Column for overlay text
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(GrayOverlay.copy(.7f)) // transparent
                .padding(
                    start = 4.dp, top = 16.dp, end = 4.dp, bottom = 16.dp
                )
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Title text
            AppText(
                text = champion.name,
                style = MaterialTheme.typography.headlineSmall.copy(color = ChampTextWhite),
            )

            // Description text
            AppText(
                text = champion.title,
                style = MaterialTheme.typography.bodySmall.copy(color = ChampTextWhite),
            )
        }
    }
}
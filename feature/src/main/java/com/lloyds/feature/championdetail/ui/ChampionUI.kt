package com.lloyds.feature.championdetail.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lloyds.domain.model.Champion
import com.lloyds.feature.shared.ui.AppText
import com.lloyds.feature.shared.ui.ChampImage
import com.lloyds.feature.theme.ChampTextWhite
import com.lloyds.feature.theme.GrayOverlay


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChampionUI(champion: Champion, onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            DetailTopBar(
                title = champion.name, onBackPressed = onBackPressed
            )
        }, containerColor = Color.Transparent
    ) { _ ->
        ChampOverlay(champion = champion)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    title: String, onBackPressed: () -> Unit
) {
    TopAppBar(title = {
        AppText(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(color = ChampTextWhite),
        )
    }, modifier = Modifier.statusBarsPadding(), navigationIcon = {
        IconButton(onClick = onBackPressed) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = ChampTextWhite
            )
        }
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Transparent
    )
    )
}

@Composable
private fun ChampOverlay(champion: Champion) {
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
                ChampionDetailTexts(champion = champion)
            }
        }
    }
}

@Composable
private fun ChampionDetailTexts(champion: Champion) {
    // Title text
    AppText(
        text = champion.name,
        style = MaterialTheme.typography.headlineSmall.copy(color = ChampTextWhite),
    )

    // Description text
    AppText(
        text = champion.title,
        style = MaterialTheme.typography.titleSmall.copy(color = ChampTextWhite),
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Lore text
    AppText(
        text = champion.lore,
        style = MaterialTheme.typography.bodySmall.copy(color = ChampTextWhite)
    )
}
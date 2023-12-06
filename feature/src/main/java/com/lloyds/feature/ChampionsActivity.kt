package com.lloyds.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.lloyds.feature.championdetail.viewmodel.ChampionDetailViewModel
import com.lloyds.feature.champions.viewmodel.ChampionListViewModel
import com.lloyds.feature.navigation.ChampionsNavHost
import com.lloyds.feature.theme.LloydsAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val championListViewModel: ChampionListViewModel by viewModels()
    private val championDetailViewModel: ChampionDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LloydsAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ChampionsNavHost(
                        championListViewModel = championListViewModel,
                        championDetailViewModel = championDetailViewModel
                    )
                }
            }
        }
    }
}
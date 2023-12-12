package com.lloyds.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lloyds.feature.championdetail.ui.ChampionDetailScreen
import com.lloyds.feature.champions.ui.ChampionListScreen

@Composable
fun ChampionsNavHost() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "champions_list") {
        composable(route = "champions_list") {
            ChampionListScreen() {
                navController.navigate("champion_detail/$it")
            }
        }
        composable(
            route = "champion_detail/{id}", arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("id")?.let { champ ->
                ChampionDetailScreen(id = champ) {
                    navController.navigateUp()
                }
            }
        }
    }
}
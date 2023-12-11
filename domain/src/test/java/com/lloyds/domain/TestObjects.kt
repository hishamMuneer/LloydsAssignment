package com.lloyds.domain

import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap

fun createChampionMap(): ChampionMap {
    return ChampionMap(champMap = mapOf("Aatrox" to createChampion()))
}

fun createChampion(): Champion {
    return Champion(
        id = "Aatrox",
        name = "Aatrox",
        title = "The Darkin Blade",
        blurb = "Blurb here",
        lore = "Lore here",
        tags = listOf("Fighter", "Tank"),
        image = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg"
    )
}

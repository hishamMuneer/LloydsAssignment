package com.lloyds.data

import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap

const val AATROX = "Aatrox"
const val TAG_FIGHTER = "Fighter"
const val TAG_TANK = "Tank"

const val AATROX_TITLE = "The Darkin Blade"
const val AATROX_BLURB = "Blurb here"
const val AATROX_LORE = "Lore here"
const val AATROX_IMAGE = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg"
const val AATROX_SPLASH_IMAGE = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg"

const val API_VERSION = "13.12.1"

internal fun createChampionMap(): ChampionMap {
    return ChampionMap(champMap = mapOf(AATROX to createChampion()))
}

internal fun createChampion(
    id: String = AATROX,
    name: String = AATROX,
    title: String = AATROX_TITLE,
    blurb: String = AATROX_BLURB,
    lore: String = AATROX_LORE,
    tags: List<String> = listOf(TAG_FIGHTER, TAG_TANK),
    image: String = AATROX_IMAGE
): Champion {
    return Champion(id, name, title, blurb, lore, tags, image)
}
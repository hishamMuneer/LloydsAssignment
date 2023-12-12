package com.lloyds.feature

import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap

private const val TAG_MAGE = "Mage"
private const val TAG_FIGHTER = "Fighter"
private const val TAG_TANK = "Tank"

const val AATROX = "Aatrox"
private const val AATROX_TITLE = "The Darkin Blade"
private const val AATROX_BLURB = "Blurb here"
private const val AATROX_LORE = "Lore here"
private const val AATROX_IMAGE = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg"

private const val SOL = "AurelionSol"
private const val SOL_NAME = "Aurelion Sol"
private const val SOL_TITLE = "The Star Forger"
private const val SOL_BLURB = "Aurelion Sol once graced the vast emptiness of the cosmos with celestial wonders of his own devising. Now, he is forced to wield his awesome power at the behest of a space-faring empire that tricked him into servitude."
private const val SOL_LORE = ""
private const val SOL_IMAGE = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/AurelionSol_0.jpg"

internal fun createChampionMap(): ChampionMap {
    return ChampionMap(champMap = mapOf(AATROX to createChampion()))
}

internal fun createMultipleChampionMap(): ChampionMap {
    return ChampionMap(
        champMap = mapOf(
            AATROX to createChampion(),
            SOL to createChampion(
                id = SOL,
                name = SOL_NAME,
                title = SOL_TITLE,
                blurb = SOL_BLURB,
                lore = SOL_LORE,
                tags = listOf(TAG_MAGE),
                image = SOL_IMAGE
            )
        )
    )
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
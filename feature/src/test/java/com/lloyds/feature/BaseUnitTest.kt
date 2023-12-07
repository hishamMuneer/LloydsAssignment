package com.lloyds.feature

import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import io.mockk.clearAllMocks
import org.junit.After

open class BaseUnitTest {

    @After
    open fun tearDown() {
        clearAllMocks()
    }

    internal fun createChampionMap(): ChampionMap {
        return ChampionMap(data = mapOf("Aatrox" to createChampion()))
    }

    internal fun createMultipleChampionMap(): ChampionMap {
        return ChampionMap(
            data = mapOf(
                "Aatrox" to createChampion(),
                "AurelionSol" to createChampion(
                    id = "AurelionSol",
                    name = "Aurelion Sol",
                    title = "The Star Forger",
                    blurb = "Aurelion Sol once graced the vast emptiness of the cosmos with celestial wonders of his own devising. Now, he is forced to wield his awesome power at the behest of a space-faring empire that tricked him into servitude.",
                    lore = "",
                    tags = listOf("Mage"),
                    image = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/AurelionSol_0.jpg"
                )
            )
        )
    }

    internal fun createChampion(
        id: String = "Aatrox",
        name: String = "Aatrox",
        title: String = "The Darkin Blade",
        blurb: String = "Blurb here",
        lore: String = "Lore here",
        tags: List<String> = listOf("Fighter", "Tank"),
        image: String = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg"
    ): Champion {
        return Champion(id, name, title, blurb, lore, tags, image)
    }
}
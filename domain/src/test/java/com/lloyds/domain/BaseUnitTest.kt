package com.lloyds.domain

import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import io.mockk.clearAllMocks
import org.junit.After

open class BaseUnitTest {

    @After
    fun tearDown() {
        clearAllMocks()
    }

    internal fun createChampionMap(): ChampionMap {
        return ChampionMap(data = mapOf("Aatrox" to createChampion()))
    }

    internal fun createChampion(): Champion {
        return Champion(
            id = "Aatrox",
            name = "Aatrox",
            title = "The Darkin Blade",
            blurb = "Blurb here",
            lore = "Lore here",
            tags = listOf("Fighter", "Tank"),
            image = "https://example.com/cdn/img/champion/loading/Aatrox_0.jpg"
        )
    }
}
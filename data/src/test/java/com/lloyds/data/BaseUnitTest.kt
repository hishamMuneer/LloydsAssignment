package com.lloyds.data

import com.lloyds.data.model.APIChampion
import com.lloyds.data.model.APIChampionDetails
import com.lloyds.data.model.APIChampionMap
import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import io.mockk.clearAllMocks
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After

open class BaseUnitTest {

    @After
    fun tearDown() {
        clearAllMocks()
    }

    internal fun createApiChampionDetails(): APIChampionDetails {
        return APIChampionDetails(data = mapOf("Aatrox" to createApiChampion()), version = "1.0")
    }

    internal fun createApiChampionMap(): APIChampionMap {
        return APIChampionMap(data = mapOf("Aatrox" to createApiChampion()), version = "1.0")
    }

    internal fun createApiChampionMap(
        championData: Map<String, APIChampion> = mapOf("Aatrox" to createApiChampion())
    ): APIChampionMap {
        return APIChampionMap(data = championData, version = "1.0")
    }

    internal fun createApiChampion(
        id: String = "Aatrox",
        name: String = "Aatrox",
        title: String = "The Darkin Blade",
        blurb: String = "Blurb here",
        lore: String? = "Lore here",
        tags: List<String> = listOf("Fighter", "Tank")
    ): APIChampion {
        return APIChampion(id, name, title, blurb, lore, tags)
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
            image = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg"
        )
    }

    internal fun createEmptyMapChampionDetails(): APIChampionDetails {
        return APIChampionDetails(mapOf(), version = "13.12.1")
    }
    internal fun emptyResponseBody() = "".toResponseBody(null)

}
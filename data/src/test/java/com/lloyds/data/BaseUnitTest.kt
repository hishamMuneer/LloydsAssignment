package com.lloyds.data

import com.lloyds.data.model.APIChampion
import com.lloyds.data.model.APIChampionDetails
import com.lloyds.data.model.APIChampionMap
import io.mockk.clearAllMocks
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After

open class BaseUnitTest {

    @After
    fun tearDown() {
        clearAllMocks()
    }

    internal fun createApiChampionDetails(): APIChampionDetails {
        return APIChampionDetails(
            data = mapOf(AATROX to createApiChampion()),
            version = API_VERSION
        )
    }

    internal fun createApiChampionMap(): APIChampionMap {
        return APIChampionMap(data = mapOf(AATROX to createApiChampion()), version = API_VERSION)
    }

    internal fun createApiChampionMap(
        championData: Map<String, APIChampion> = mapOf(AATROX to createApiChampion())
    ): APIChampionMap {
        return APIChampionMap(data = championData, version = API_VERSION)
    }

    internal fun createApiChampion(
        id: String = AATROX,
        name: String = AATROX,
        title: String = AATROX_TITLE,
        blurb: String = AATROX_BLURB,
        lore: String? = AATROX_LORE,
        tags: List<String> = listOf(TAG_FIGHTER, TAG_TANK)
    ): APIChampion {
        return APIChampion(id, name, title, blurb, lore, tags)
    }

    internal fun createEmptyMapChampionDetails(): APIChampionDetails {
        return APIChampionDetails(mapOf(), version = API_VERSION)
    }

    internal fun emptyResponseBody() = "".toResponseBody(null)

}
package com.lloyds.data.mapper

import com.lloyds.data.AATROX
import com.lloyds.data.AATROX_IMAGE
import com.lloyds.data.AATROX_LORE
import com.lloyds.data.AATROX_TITLE
import com.lloyds.data.BaseUnitTest
import com.lloyds.data.TAG_FIGHTER
import com.lloyds.data.TAG_TANK
import com.lloyds.data.model.APIChampionMap
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ChampionListApiToDomainMapperTest : BaseUnitTest() {

    private lateinit var mapper: ChampionListApiToDomainMapper
    private lateinit var apiChampionMap: APIChampionMap
    @Before
    fun setUp() {
        // Given
        mapper = ChampionListApiToDomainMapper()
        apiChampionMap = createApiChampionMap()
    }

    @Test
    fun basicMappingTest() {

        // When
        val championMap = mapper.map(apiChampionMap)

        // Then
        assertEquals(1, championMap.champMap.size)
        assertTrue(championMap.champMap.containsKey(AATROX))

        val champion = championMap.champMap[AATROX]
        assertNotNull(champion)
        // Then
        champion!!.apply {
            assertEquals(AATROX, id)
            assertEquals(AATROX, name)
            assertEquals(AATROX_TITLE, title)
            assertEquals(AATROX_LORE, lore)
            assertEquals(TAG_FIGHTER, tags[0])
            assertEquals(TAG_TANK, tags[1])
            assertEquals(AATROX_IMAGE, image)
        }
    }

    @Test //(expected = IllegalArgumentException::class)
    fun emptyMapDataTest() {
        // Given
        val apiChampionMap = createApiChampionMap(mapOf())
        val mapper = ChampionListApiToDomainMapper()

        // When
        val championMap = mapper.map(apiChampionMap)

        // Then
        assertEquals(0, championMap.champMap.size)
    }

    @Test(timeout = 100)
    fun performanceTest() {
        // Given
        val apiChampionDetails = createApiChampionMap()
        mapper.map(apiChampionDetails)
        // sleep(2000)
    }

    @Test
    fun multipleChampionsMappingTest() {
        // Given
        val apiChampionMap = createApiChampionMap(
            mapOf(
                AATROX to createApiChampion(), "Ahri" to createApiChampion(
                    id = "Ahri", name = "Ahri", title = "The Nine-Tailed Fox"
                )
            )
        )

        // When
        val championMap = mapper.map(apiChampionMap)

        // Then
        assertEquals(2, championMap.champMap.size)
        assertTrue(championMap.champMap.containsKey(AATROX))
        assertTrue(championMap.champMap.containsKey("Ahri"))

        val ahriChampion = championMap.champMap["Ahri"]
        assertNotNull(ahriChampion)
        assertEquals("Ahri", ahriChampion?.name)
        assertEquals("The Nine-Tailed Fox", ahriChampion?.title)
    }

    @Test
    fun imageUrlConstructionTest() {
        // Given
        val apiChampionMap = createApiChampionMap()

        // When
        val championMap = mapper.map(apiChampionMap)

        // Then
        val aatroxChampion = championMap.champMap[AATROX]
        assertNotNull(aatroxChampion)
        assertEquals(
            AATROX_IMAGE, aatroxChampion?.image
        )
    }
}
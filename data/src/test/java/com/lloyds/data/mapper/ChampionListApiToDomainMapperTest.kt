package com.lloyds.data.mapper

import com.lloyds.data.BaseUnitTest
import com.lloyds.data.model.APIChampionMap
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ChampionListApiToDomainMapperTest: BaseUnitTest() {

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
        assertTrue(championMap.champMap.containsKey("Aatrox"))

        val champion = championMap.champMap["Aatrox"]
        assertNotNull(champion)
        assertEquals("Aatrox", champion?.name)
        assertEquals("The Darkin Blade", champion?.title)
        assertEquals("Blurb here", champion?.blurb)
        assertEquals("Lore here", champion?.lore)
        assertEquals(listOf("Fighter", "Tank"), champion?.tags)
        assertEquals("https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg", champion?.image)
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
                "Aatrox" to createApiChampion(),
                "Ahri" to createApiChampion(
                    id = "Ahri",
                    name = "Ahri",
                    title = "The Nine-Tailed Fox"
                )
            )
        )

        // When
        val championMap = mapper.map(apiChampionMap)

        // Then
        assertEquals(2, championMap.champMap.size)
        assertTrue(championMap.champMap.containsKey("Aatrox"))
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
        val aatroxChampion = championMap.champMap["Aatrox"]
        assertNotNull(aatroxChampion)
        assertEquals(
            "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg",
            aatroxChampion?.image
        )
    }
}
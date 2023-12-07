package com.lloyds.data.mapper

import com.lloyds.data.BaseUnitTest
import com.lloyds.data.BuildConfig
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
// import java.lang.Thread.sleep

class ChampionDetailApiToDomainMapperTest : BaseUnitTest() {

    private lateinit var mapper: ChampionDetailApiToDomainMapper

    @Before
    fun setUp() {
        mapper = ChampionDetailApiToDomainMapper()
    }

    @Test
    fun basicMappingTest() {
        // When
        val champion = mapper.map(createApiChampionDetails())

        // Then
        assertEquals("Aatrox", champion.id)
        assertEquals("Aatrox", champion.name)
        assertEquals("The Darkin Blade", champion.title)
        assertEquals("Lore here", champion.lore)
        assertEquals("Fighter", champion.tags[0])
        assertEquals("Tank", champion.tags[1])
        assertEquals(BuildConfig.BASE_URL + "/cdn/img/champion/splash/Aatrox_0.jpg", champion.image)
    }

    @Test(expected = NoSuchElementException::class)
    fun `empty map champion`() {
        // Given
        val apiChampionDetails = createEmptyMapChampionDetails()
        // When
        mapper.map(apiChampionDetails)
    }

    @Test(timeout = 100)
    fun performanceTest() {
        // Given
        val apiChampionDetails = createApiChampionDetails()
        mapper.map(apiChampionDetails)
        // sleep(2000)
    }
}
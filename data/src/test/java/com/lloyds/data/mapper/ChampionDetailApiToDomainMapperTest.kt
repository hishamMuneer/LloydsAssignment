package com.lloyds.data.mapper

import com.lloyds.data.AATROX
import com.lloyds.data.AATROX_LORE
import com.lloyds.data.AATROX_SPLASH_IMAGE
import com.lloyds.data.AATROX_TITLE
import com.lloyds.data.BaseUnitTest
import com.lloyds.data.TAG_FIGHTER
import com.lloyds.data.TAG_TANK
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
        assertEquals(AATROX, champion.id)
        assertEquals(AATROX, champion.name)
        assertEquals(AATROX_TITLE, champion.title)
        assertEquals(AATROX_LORE, champion.lore)
        assertEquals(TAG_FIGHTER, champion.tags[0])
        assertEquals(TAG_TANK, champion.tags[1])
        assertEquals(AATROX_SPLASH_IMAGE, champion.image)
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
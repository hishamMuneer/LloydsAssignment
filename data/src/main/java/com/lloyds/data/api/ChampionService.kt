package com.lloyds.data.api

import com.lloyds.data.model.APIChampion
import com.lloyds.data.model.APIChampionMap
import retrofit2.http.GET
import retrofit2.http.Path

interface ChampionService {

    @GET("/cdn/13.23.1/data/en_US/champion.json")
    suspend fun getChampionList(): APIChampionMap

    // Sample URL: https://ddragon.leagueoflegends.com/cdn/13.23.1/data/en_US/champion/Aatrox.json
    @GET("/cdn/13.23.1/data/en_US/champion/{champ}.json")
    suspend fun getChampion(@Path("champ") champion: String): APIChampion
}
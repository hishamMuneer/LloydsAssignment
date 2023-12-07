package com.lloyds.data.api

import com.lloyds.data.model.APIChampionDetails
import com.lloyds.data.model.APIChampionMap
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChampionService {

    @GET("/cdn/13.23.1/data/en_US/champion.json")
    suspend fun getChampionList(): Response<APIChampionMap>

    // Sample URL: https://ddragon.leagueoflegends.com/cdn/13.23.1/data/en_US/champion/Aatrox.json
    @GET("/cdn/13.23.1/data/en_US/champion/{id}.json")
    suspend fun getChampion(@Path("id") id: String): Response<APIChampionDetails>
}
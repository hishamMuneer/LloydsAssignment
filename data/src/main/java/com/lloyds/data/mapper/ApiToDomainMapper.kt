package com.lloyds.data.mapper

import com.lloyds.data.model.APIChampion
import com.lloyds.data.model.APIChampionMap
import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import javax.inject.Inject

class ApiModelMapper @Inject constructor() {
    fun mapApiChampionToChampion(apiModel: APIChampion): Champion {
        return Champion(
            id = apiModel.id,
            name = apiModel.name,
            title = apiModel.title,
            blurb = apiModel.blurb,
            lore = apiModel.lore ?: "", // or empty String
            tags = apiModel.tags

        )
    }

    fun mapApiChampionMapToChampionMap(apiChampionMap: APIChampionMap): ChampionMap {
        val championMapData = apiChampionMap.data.mapValues { (_, apiChampion) ->
            mapApiChampionToChampion(apiChampion)
        }
        return ChampionMap(championMapData)
    }
}
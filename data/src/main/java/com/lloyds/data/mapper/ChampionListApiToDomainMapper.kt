package com.lloyds.data.mapper

import com.lloyds.data.BuildConfig
import com.lloyds.data.model.APIChampion
import com.lloyds.data.model.APIChampionMap
import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import javax.inject.Inject

class ChampionListApiToDomainMapper @Inject constructor() :
    DataMapper<APIChampionMap, ChampionMap> {

    override fun map(data: APIChampionMap): ChampionMap {
        val championMapData = data.data.mapValues { (_, apiChampion) ->
            mapInternals(apiChampion)
        }
        return ChampionMap(championMapData)
    }

    private fun mapInternals(data: APIChampion): Champion {
        data.apply {
            return Champion(
                id = id,
                name = name,
                title = title,
                blurb = blurb,
                lore = lore ?: "",
                tags = tags,
                // http://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
                image = BuildConfig.BASE_URL + "/cdn/img/champion/loading/${id}_0.jpg"
            )
        }
    }
}

package com.lloyds.data.mapper

import com.lloyds.data.BuildConfig
import com.lloyds.data.model.APIChampionDetails
import com.lloyds.domain.model.Champion
import javax.inject.Inject

class ChampionDetailApiToDomainMapper @Inject constructor() :
    DataMapper<APIChampionDetails, Champion> {

    companion object {
        private const val CHAMPION_SPLASH_IMAGE_FORMAT = "/cdn/img/champion/splash/%s_0.jpg"
    }

    override fun map(data: APIChampionDetails): Champion {
        // converting the map to list to get the first element as we know already only 1 item will be received from the api
        data.data.toList().first().second.apply {
            return Champion(
                id = id,
                name = name,
                title = title,
                blurb = blurb,
                lore = lore ?: "",
                tags = tags,
                // Image link is not returned from the server, instead we have a predefined format for loading the images.
                // Check http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
                image = BuildConfig.BASE_URL + CHAMPION_SPLASH_IMAGE_FORMAT.format(id)
            )
        }
    }
}
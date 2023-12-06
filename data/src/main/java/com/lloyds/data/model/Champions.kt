package com.lloyds.data.model

import kotlinx.serialization.Serializable

/**
 * Data model for champion list from the server.
 * Note: Data is stored in a map with the champion id as the key.
 * For reference, please check the [/docs/champList.json] file
 */
@Serializable
data class APIChampionMap(
    val data: Map<String, APIChampion>,
    val version: String
)

@Serializable
data class APIChampion(
    val id: String,
    val name: String,
    val title: String,
    val blurb: String,
    val lore: String? = null, // lore is null when champ list is fetched. Both APIs (champ list and champ details) utilize the same champion model
    val tags: List<String>
)

/**
 * Champion Detail has the same structure as [APIChampionMap] but only contains one champion.
 */
@Serializable
data class APIChampionDetails(
    val data: Map<String, APIChampion>, val version: String
)


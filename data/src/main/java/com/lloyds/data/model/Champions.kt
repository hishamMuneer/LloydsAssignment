package com.lloyds.data.model


data class APIChampionMap(
    val data: Map<String, APIChampion>,
    val version: String
)

data class APIChampion(
    val id: String,
    val name: String,
    val title: String,
    val blurb: String,
    val lore: String? = null, // lore is null when champ list is fetched.
    val tags: List<String>,
    val version: String
)


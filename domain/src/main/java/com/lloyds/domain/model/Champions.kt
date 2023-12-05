package com.lloyds.domain.model

data class ChampionMap(val data: Map<String, Champion>)

data class Champion(
    val id: String,
    val name: String,
    val title: String,
    val blurb: String,
    val lore: String,
    val tags: List<String>
)
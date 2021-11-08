package com.starwars.starwarsviewer.domain.planet.model

import com.google.gson.annotations.SerializedName

data class PlanetsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Planet>
    )
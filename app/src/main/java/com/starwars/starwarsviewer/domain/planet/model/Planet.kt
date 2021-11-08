package com.starwars.starwarsviewer.domain.planet.model

import com.google.gson.annotations.SerializedName

data class Planet(
    @SerializedName("climate")
    val climate: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("diameter")
    val diameter: String,
    @SerializedName("edited")
    val edited: String,
    @SerializedName("films")
    val films: List<String>,
    @SerializedName("gravity")
    val gravity: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("orbitalPeriod")
    val orbitalPeriod: String,
    @SerializedName("population")
    val population: String,
    @SerializedName("residents")
    val residents: List<String>,
    @SerializedName("rotation_period")
    val rotationPeriod: String,
    @SerializedName("surface_water")
    val surfaceWater: String,
    @SerializedName("terrain")
    val terrain: String,
    @SerializedName("url")
    val url: String
)
package com.starwars.starwarsviewer.network.planet

import com.starwars.starwarsviewer.domain.planet.model.Planet
import com.starwars.starwarsviewer.domain.planet.model.PlanetsResponse
import com.starwars.starwarsviewer.repo.Repo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlanetsService : Repo {
    @GET("/api/planets/{id}")
    suspend fun fetchPlanet(@Path("id") id: Int): Planet

    @GET("/api/planets")
    suspend fun fetchPlanetPage(@Query("page") pageNumber: Int): PlanetsResponse
}
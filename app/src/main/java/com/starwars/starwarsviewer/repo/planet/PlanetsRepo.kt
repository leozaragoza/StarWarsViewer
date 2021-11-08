package com.starwars.starwarsviewer.repo.planet

import com.starwars.starwarsviewer.domain.planet.model.Planet
import com.starwars.starwarsviewer.domain.planet.model.PlanetsResponse

interface PlanetsRepo {
    suspend fun fetchPlanet(id: Int): Planet
    suspend fun fetchPlanetsPage(pageNumber: Int): PlanetsResponse
}
package com.starwars.starwarsviewer.repo.planet

import com.starwars.starwarsviewer.domain.planet.model.Planet
import com.starwars.starwarsviewer.domain.planet.model.PlanetsResponse
import com.starwars.starwarsviewer.repo.Repo

interface PlanetsRepo : Repo {
    suspend fun fetchPlanet(id: Int): Planet
    suspend fun fetchPlanetsPage(pageNumber: Int): PlanetsResponse
}
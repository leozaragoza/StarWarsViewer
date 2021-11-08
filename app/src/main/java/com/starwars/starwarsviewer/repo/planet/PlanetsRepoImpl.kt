package com.starwars.starwarsviewer.repo.planet

import com.starwars.starwarsviewer.network.planet.PlanetsService
import javax.inject.Inject

//TODO: Handle network errors
class PlanetsRepoImpl @Inject constructor(private val planetsService: PlanetsService): PlanetsRepo {
    override suspend fun fetchPlanet(id: Int) = planetsService.fetchPlanet(id)
    override suspend fun fetchPlanetsPage(pageNumber: Int) = planetsService.fetchPlanetPage(pageNumber)
}
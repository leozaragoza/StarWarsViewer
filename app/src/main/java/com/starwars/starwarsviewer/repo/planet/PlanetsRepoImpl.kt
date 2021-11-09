package com.starwars.starwarsviewer.repo.planet

import com.starwars.starwarsviewer.network.planet.PlanetsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

//TODO: Wrap responses into Result object and Handle network errors
class PlanetsRepoImpl @Inject constructor(private val planetsService: PlanetsService): PlanetsRepo {
    override suspend fun fetchPlanet(id: Int) = withContext(Dispatchers.IO) { planetsService.fetchPlanet(id) }
    override suspend fun fetchPlanetsPage(pageNumber: Int) = withContext(Dispatchers.IO) { planetsService.fetchPlanetPage(pageNumber) }
}
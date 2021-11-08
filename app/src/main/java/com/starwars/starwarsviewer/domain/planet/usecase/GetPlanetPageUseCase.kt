package com.starwars.starwarsviewer.domain.planet.usecase

import com.starwars.starwarsviewer.domain.planet.model.PlanetsResponse
import com.starwars.starwarsviewer.repo.planet.PlanetsRepo
import javax.inject.Inject

class GetPlanetPageUseCase @Inject constructor(private val planetsRepo: PlanetsRepo) {
    suspend fun getPlanetPage(pageNumber: Int): PlanetsResponse {
        return planetsRepo.fetchPlanetsPage(pageNumber)
    }
}
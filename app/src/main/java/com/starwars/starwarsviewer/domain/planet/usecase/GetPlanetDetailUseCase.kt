package com.starwars.starwarsviewer.domain.planet.usecase

import com.starwars.starwarsviewer.domain.planet.model.Planet
import com.starwars.starwarsviewer.repo.planet.PlanetsRepo
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(private val planetsRepo: PlanetsRepo) {
    suspend fun getPlanetDetail(id: Int): Planet {
        return planetsRepo.fetchPlanet(id)
    }
}
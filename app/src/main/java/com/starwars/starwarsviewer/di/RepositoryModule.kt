package com.starwars.starwarsviewer.di

import com.starwars.starwarsviewer.network.planet.PlanetsService
import com.starwars.starwarsviewer.repo.planet.PlanetsRepo
import com.starwars.starwarsviewer.repo.planet.PlanetsRepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providePlanetsRepo(planetsService: PlanetsService): PlanetsRepo = PlanetsRepoImpl(planetsService)
}
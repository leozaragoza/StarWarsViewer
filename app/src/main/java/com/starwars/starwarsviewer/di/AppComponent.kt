package com.starwars.starwarsviewer.di

import com.starwars.starwarsviewer.application.StarWarsApplication
import com.starwars.starwarsviewer.ui.planet.PlanetDetailFragment
import com.starwars.starwarsviewer.ui.planet.PlanetsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(application: StarWarsApplication)

    fun inject(planetsFragment: PlanetsFragment)

    fun inject(planetDetailFragment: PlanetDetailFragment)
}
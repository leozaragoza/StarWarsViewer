package com.starwars.starwarsviewer.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.starwars.starwarsviewer.di.annotations.Annotations
import com.starwars.starwarsviewer.di.factory.ViewModelFactory
import com.starwars.starwarsviewer.ui.viewmodel.planet.PlanetDetailViewModel
import com.starwars.starwarsviewer.ui.viewmodel.planet.PlanetListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @Annotations.ViewModelKey(PlanetListViewModel::class)
    abstract fun planetListViewModel(viewModel: PlanetListViewModel): ViewModel

    @Binds
    @IntoMap
    @Annotations.ViewModelKey(PlanetDetailViewModel::class)
    abstract fun planetDetailViewModel(viewModel: PlanetDetailViewModel): ViewModel
}
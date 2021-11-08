package com.starwars.starwarsviewer.ui.viewmodel.planet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starwars.starwarsviewer.domain.planet.model.Planet
import com.starwars.starwarsviewer.domain.planet.usecase.GetPlanetDetailUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlanetDetailViewModel @Inject constructor(private val getPlanetDetailUseCase: GetPlanetDetailUseCase): ViewModel() {

    val planet = MutableLiveData<Planet>()

    fun getPlanet(id: Int): LiveData<Planet> {
        viewModelScope.launch {
            planet.postValue(getPlanetDetailUseCase.getPlanetDetail(id))
        }
        return planet
    }
}
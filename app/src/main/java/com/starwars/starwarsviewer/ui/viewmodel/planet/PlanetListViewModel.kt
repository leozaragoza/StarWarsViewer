package com.starwars.starwarsviewer.ui.viewmodel.planet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.starwars.starwarsviewer.domain.planet.usecase.GetPlanetPageUseCase
import com.starwars.starwarsviewer.ui.paging.PlanetsPagingSource
import javax.inject.Inject

class PlanetListViewModel @Inject constructor(private val getPlanetPageUseCase: GetPlanetPageUseCase): ViewModel() {
    val pagedFlow = Pager(
        PagingConfig(pageSize = 60)
    ) {
        PlanetsPagingSource(getPlanetPageUseCase)
    }.flow.cachedIn(viewModelScope)
}
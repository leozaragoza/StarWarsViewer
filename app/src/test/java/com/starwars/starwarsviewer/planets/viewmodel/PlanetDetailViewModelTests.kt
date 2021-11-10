package com.starwars.starwarsviewer.planets.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.starwars.starwarsviewer.domain.planet.model.Planet
import com.starwars.starwarsviewer.domain.planet.usecase.GetPlanetDetailUseCase
import com.starwars.starwarsviewer.ui.viewmodel.planet.PlanetDetailViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class PlanetDetailViewModelTests {

    private lateinit var planetDetailViewModel: PlanetDetailViewModel

    @Mock
    private lateinit var getPlanetDetailUseCase: GetPlanetDetailUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var planet: Planet

    @Mock
    private lateinit var planet2: Planet

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        planetDetailViewModel = PlanetDetailViewModel(getPlanetDetailUseCase)
    }

    @Test
    fun `verify calling get planet detail`() {
        runBlocking {
            Mockito.`when`(getPlanetDetailUseCase.getPlanetDetail(1)).thenReturn(planet)
            val result = planetDetailViewModel.getPlanet(1).value
            Mockito.verify(getPlanetDetailUseCase).getPlanetDetail(1)
            Assert.assertEquals(result, planet)
        }
    }

    @Test
    fun `verify calling get planet detail with different ids`() {
        runBlocking {
            Mockito.`when`(getPlanetDetailUseCase.getPlanetDetail(1)).thenReturn(planet)
            Mockito.`when`(getPlanetDetailUseCase.getPlanetDetail(2)).thenReturn(planet2)
            val result = planetDetailViewModel.getPlanet(1).value
            planetDetailViewModel.cleanData()
            val result2 = planetDetailViewModel.getPlanet(2).value
            Mockito.verify(getPlanetDetailUseCase).getPlanetDetail(1)
            Mockito.verify(getPlanetDetailUseCase).getPlanetDetail(2)
            Assert.assertEquals(result, planet)
            Assert.assertEquals(result2, planet2)
            Assert.assertNotEquals(result, planet2)
            Assert.assertNotEquals(result2, planet)
        }
    }
}
package com.starwars.starwarsviewer.planets.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.starwars.starwarsviewer.domain.planet.model.Planet
import com.starwars.starwarsviewer.domain.planet.usecase.GetPlanetDetailUseCase
import com.starwars.starwarsviewer.repo.planet.PlanetsRepo
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
class GetPlanetDetailUseCaseTests {
    private lateinit var getPlanetDetailUseCase: GetPlanetDetailUseCase

    @Mock
    private lateinit var planetsRepo: PlanetsRepo

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var planet: Planet

    @Mock
    private lateinit var planet2: Planet

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getPlanetDetailUseCase = GetPlanetDetailUseCase(planetsRepo)
    }

    @Test
    fun `verify calling get planet detail use case`() {
        runBlocking {
            Mockito.`when`(planetsRepo.fetchPlanet(1)).thenReturn(planet)
            val result = getPlanetDetailUseCase.getPlanetDetail(1)
            Mockito.verify(planetsRepo).fetchPlanet(1)
            Assert.assertEquals(result, planet)
        }
    }

    @Test
    fun `verify calling get planet detail with different ids return different results`() {
        runBlocking {
            Mockito.`when`(planetsRepo.fetchPlanet(1)).thenReturn(planet)
            Mockito.`when`(planetsRepo.fetchPlanet(2)).thenReturn(planet2)
            val result = getPlanetDetailUseCase.getPlanetDetail(1)
            val result2 = getPlanetDetailUseCase.getPlanetDetail(2)
            Mockito.verify(planetsRepo).fetchPlanet(1)
            Mockito.verify(planetsRepo).fetchPlanet(2)
            Assert.assertEquals(result, planet)
            Assert.assertEquals(result2, planet2)
            Assert.assertNotEquals(result, planet2)
            Assert.assertNotEquals(result2, planet)
        }
    }
}
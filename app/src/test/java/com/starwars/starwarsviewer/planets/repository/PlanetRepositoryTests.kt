package com.starwars.starwarsviewer.planets.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.starwars.starwarsviewer.domain.planet.model.Planet
import com.starwars.starwarsviewer.domain.planet.model.PlanetsResponse
import com.starwars.starwarsviewer.network.planet.PlanetsService
import com.starwars.starwarsviewer.repo.planet.PlanetsRepo
import com.starwars.starwarsviewer.repo.planet.PlanetsRepoImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class PlanetRepositoryTests {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var planetsService: PlanetsService

    @Mock
    private lateinit var planet: Planet

    @Mock
    private lateinit var planet2: Planet

    @Mock
    private lateinit var planetsResponse: PlanetsResponse

    @Mock
    private lateinit var planetsResponse2: PlanetsResponse

    private lateinit var planetsRepo: PlanetsRepo

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        planetsRepo = PlanetsRepoImpl(planetsService)
    }

    @Test
    fun `verify calling planet repo with id returns the right planet`() {
        runBlocking {
            `when`(planetsService.fetchPlanet(1)).thenReturn(planet)
            val result = planetsRepo.fetchPlanet(1)
            verify(planetsService).fetchPlanet(1)
            Assert.assertEquals(result, planet)
        }
    }

    @Test
    fun `verify calling planet repo with different ids return different planets`() {
        runBlocking {
            `when`(planetsService.fetchPlanet(1)).thenReturn(planet)
            `when`(planetsService.fetchPlanet(2)).thenReturn(planet2)
            val result = planetsRepo.fetchPlanet(1)
            val result2 = planetsRepo.fetchPlanet(2)
            verify(planetsService).fetchPlanet(1)
            verify(planetsService).fetchPlanet(2)
            Assert.assertNotEquals(result, planet2)
            Assert.assertNotEquals(result2, planet)
            Assert.assertEquals(result, planet)
            Assert.assertEquals(result2, planet2)
        }
    }

    @Test
    fun `verify calling planet repo page`() {
        runBlocking {
            `when`(planetsService.fetchPlanetPage(1)).thenReturn(planetsResponse)
            val result = planetsRepo.fetchPlanetsPage(1)
            verify(planetsService).fetchPlanetPage(1)
            Assert.assertEquals(result, planetsResponse)
        }
    }

    @Test
    fun `verify calling planet repo page with different pages return different results`() {
        runBlocking {
            `when`(planetsService.fetchPlanetPage(1)).thenReturn(planetsResponse)
            `when`(planetsService.fetchPlanetPage(2)).thenReturn(planetsResponse2)
            val result = planetsRepo.fetchPlanetsPage(1)
            val result2 = planetsRepo.fetchPlanetsPage(2)
            verify(planetsService).fetchPlanetPage(1)
            verify(planetsService).fetchPlanetPage(2)
            Assert.assertNotEquals(result, planetsResponse2)
            Assert.assertNotEquals(result2, planetsResponse)
            Assert.assertEquals(result, planetsResponse)
            Assert.assertEquals(result2, planetsResponse2)
        }
    }
}
package com.starwars.starwarsviewer.planets.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.starwars.starwarsviewer.domain.planet.model.PlanetsResponse
import com.starwars.starwarsviewer.domain.planet.usecase.GetPlanetPageUseCase
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
class GetPlanetPageUseCaseTests {

    private lateinit var getPlanetPageUseCase: GetPlanetPageUseCase

    @Mock
    private lateinit var planetsRepo: PlanetsRepo

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var planetsResponse: PlanetsResponse

    @Mock
    private lateinit var planetsResponse2: PlanetsResponse

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getPlanetPageUseCase = GetPlanetPageUseCase(planetsRepo)
    }

    @Test
    fun `verify calling get planet page use case`() {
        runBlocking {
            Mockito.`when`(planetsRepo.fetchPlanetsPage(1)).thenReturn(planetsResponse)
            val result = getPlanetPageUseCase.getPlanetPage(1)
            Mockito.verify(planetsRepo).fetchPlanetsPage(1)
            Assert.assertEquals(result, planetsResponse)
        }
    }

    @Test
    fun `verify calling get planet page with different pages`() {
        runBlocking {
            Mockito.`when`(planetsRepo.fetchPlanetsPage(1)).thenReturn(planetsResponse)
            Mockito.`when`(planetsRepo.fetchPlanetsPage(2)).thenReturn(planetsResponse2)
            val result = getPlanetPageUseCase.getPlanetPage(1)
            val result2 = getPlanetPageUseCase.getPlanetPage(2)
            Mockito.verify(planetsRepo).fetchPlanetsPage(1)
            Mockito.verify(planetsRepo).fetchPlanetsPage(2)
            Assert.assertEquals(result, planetsResponse)
            Assert.assertEquals(result2, planetsResponse2)
            Assert.assertNotEquals(result, planetsResponse2)
            Assert.assertNotEquals(result2, planetsResponse)
        }
    }
}
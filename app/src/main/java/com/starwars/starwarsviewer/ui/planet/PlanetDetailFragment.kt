package com.starwars.starwarsviewer.ui.planet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.starwars.starwarsviewer.R
import com.starwars.starwarsviewer.application.StarWarsApplication
import com.starwars.starwarsviewer.databinding.FragmentPlanetDetailBinding
import com.starwars.starwarsviewer.di.factory.ViewModelFactory
import com.starwars.starwarsviewer.domain.planet.model.Planet
import com.starwars.starwarsviewer.ui.common.BaseFragment
import com.starwars.starwarsviewer.ui.viewmodel.planet.PlanetDetailViewModel
import com.starwars.starwarsviewer.util.Constants.Companion.PLANET_ID_BUNDLE_KEY
import com.starwars.starwarsviewer.util.extensions.show
import javax.inject.Inject

class PlanetDetailFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var planetDetailViewModel: PlanetDetailViewModel

    private lateinit var binding: FragmentPlanetDetailBinding

    private val picasso = Picasso.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StarWarsApplication.starWarsComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlanetDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        planetDetailViewModel = ViewModelProvider(this, viewModelFactory)[PlanetDetailViewModel::class.java]
        getPlanetData()
    }

    private fun getPlanetData() {
        showDetails(false)
        arguments?.getInt(PLANET_ID_BUNDLE_KEY)?.let {
            planetDetailViewModel.getPlanet(it).observe(viewLifecycleOwner, { planet ->
                populatePlanetData(planet)
            })
        }
    }

    private fun populatePlanetData(planet: Planet) {
        binding.nameValueTv.text = planet.name
        binding.climateValueTv.text = planet.climate
        binding.populationValueTv.text = planet.population
        binding.rotationPeriodValueTv.text = planet.rotationPeriod
        binding.terrainValueTv.text = planet.terrain

        val dummyImage = resources.getString(R.string.sample_planet_image)
        picasso.load(dummyImage).into(binding.detailIv)
        showDetails(true)
    }

    private fun showDetails(show: Boolean) {
        binding.progressBar.show(!show)
        binding.detailsSv.show(show)
    }
}
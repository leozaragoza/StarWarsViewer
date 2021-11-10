package com.starwars.starwarsviewer.ui.planet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.starwars.starwarsviewer.R
import com.starwars.starwarsviewer.application.StarWarsApplication
import com.starwars.starwarsviewer.databinding.FragmentPlanetsBinding
import com.starwars.starwarsviewer.di.factory.ViewModelFactory
import com.starwars.starwarsviewer.ui.adapter.PlanetAdapter
import com.starwars.starwarsviewer.ui.common.BaseFragment
import com.starwars.starwarsviewer.ui.viewmodel.planet.PlanetListViewModel
import com.starwars.starwarsviewer.util.Constants.Companion.PLANET_ID_BUNDLE_KEY
import com.starwars.starwarsviewer.util.extensions.show
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlanetsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var planetListViewModel: PlanetListViewModel

    lateinit var binding: FragmentPlanetsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StarWarsApplication.starWarsComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlanetsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        planetListViewModel = ViewModelProvider(this, viewModelFactory)[PlanetListViewModel::class.java]
        setupPlanetList()
    }

    private fun setupPlanetList() {
        val pagingAdapter = PlanetAdapter(PlanetAdapter.PlanetComparator) {
            navigateToPlanetDetail(it)
        }

        with(binding.planetsRv) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = pagingAdapter
        }

        lifecycleScope.launch {
            planetListViewModel.pagedFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }

        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar.show(loadStates.refresh is LoadState.Loading)
                //TODO: handle errors and retry
                //retry.isVisible = loadState.refresh !is LoadState.Loading
                //errorMsg.isVisible = loadState.refresh is LoadState.Error
            }
        }
    }

    private fun navigateToPlanetDetail(id: Int) {
        //TODO: use safe args, avoided to use it now because of bug with the library current version
        findNavController().navigate(R.id.action_planetsFragment_to_planetDetailFragment, Bundle().apply {
            putInt(PLANET_ID_BUNDLE_KEY, id)
        })
    }
}
package com.starwars.starwarsviewer.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.starwars.starwarsviewer.R
import com.starwars.starwarsviewer.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var notImplementedFeatureToast: Toast

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    //TODO: implement a presenter
    private fun setupListeners() {
        notImplementedFeatureToast = Toast.makeText(requireContext(), R.string.feature_not_implemented_yet, Toast.LENGTH_SHORT)
        with(binding) {
            planetsBtn.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_planetsFragment) }
            peopleBtn.setOnClickListener { notImplementedFeatureToast.show() }
        }
    }
}
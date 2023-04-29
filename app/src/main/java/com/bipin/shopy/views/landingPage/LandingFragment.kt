package com.bipin.shopy.views.landingPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bipin.shopy.R
import com.bipin.shopy.databinding.FragmentLandingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : Fragment(R.layout.fragment_landing) {

    private val viewModel: LandingVM by viewModels()
    private var binding: FragmentLandingBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLandingBinding.bind(view)
        binding?.vm = viewModel
    }

}
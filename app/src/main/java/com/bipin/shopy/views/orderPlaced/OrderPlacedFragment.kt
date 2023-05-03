package com.bipin.shopy.views.orderPlaced

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bipin.shopy.R
import com.bipin.shopy.databinding.FragmentOrderPlacedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderPlacedFragment : Fragment(R.layout.fragment_order_placed) {

    private val viewModel: OrderPlacedVM by viewModels()
    private var binding: FragmentOrderPlacedBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderPlacedBinding.bind(view)
        binding?.vm = viewModel
    }


}
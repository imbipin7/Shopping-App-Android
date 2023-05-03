package com.bipin.shopy.views.paymentmethod

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bipin.shopy.R
import com.bipin.shopy.databinding.FragmentPaymentMethodBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentMethodFragment : Fragment(R.layout.fragment_payment_method) {


    private val viewModel: PaymentMethodVM by viewModels()
    private var binding: FragmentPaymentMethodBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentMethodBinding.bind(view)
        binding?.vm = viewModel
    }


}
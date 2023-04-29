package com.bipin.shopy.views.otpverification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bipin.shopy.R
import com.bipin.shopy.databinding.FragmentOtpVerificationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpVerificationFragment : Fragment(R.layout.fragment_otp_verification) {


    private val viewModel: OtpVerificationVM by viewModels()
    private var binding: FragmentOtpVerificationBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOtpVerificationBinding.bind(view)
        binding?.vm = viewModel
    }

}
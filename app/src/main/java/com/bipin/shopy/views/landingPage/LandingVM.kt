package com.bipin.shopy.views.landingPage

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bipin.shopy.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingVM @Inject constructor() : ViewModel() {

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSignUp -> {
                view.findNavController().navigate(R.id.action_landingFragment_to_signupFragment)
            }
            R.id.tvSignIn -> {
                view.findNavController().navigate(R.id.action_landingFragment_to_loginFragment)
            }
        }
    }
}
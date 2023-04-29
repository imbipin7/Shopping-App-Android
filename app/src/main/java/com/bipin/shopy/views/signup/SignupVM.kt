package com.bipin.shopy.views.signup

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bipin.shopy.R
import com.bipin.shopy.utils.navigateBack
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupVM @Inject constructor() : ViewModel() {

    fun onClick(view: View) {
        when (view.id) {
            R.id.tvAlreadyAccount -> {
                view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
            }
            R.id.ivBack -> {
                view.navigateBack()
            }
        }
    }
}
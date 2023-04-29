package com.bipin.shopy.views.login

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bipin.shopy.R
import com.bipin.shopy.utils.navigateBack
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor() : ViewModel() {

    fun onClick(view: View) {
        when (view.id) {
            R.id.ivBack -> {
                view.navigateBack()
            }
            R.id.tvDoNotHaveAccount -> {
                view.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
            }
            R.id.tvForgotPass -> {
                view.findNavController()
                    .navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
            }
        }
    }
}
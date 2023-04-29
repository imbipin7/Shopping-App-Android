package com.bipin.shopy.views.forgotPassword

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bipin.shopy.R
import com.bipin.shopy.utils.navigateBack
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordVM @Inject constructor() : ViewModel() {

    fun onClick(view: View) {
        when (view.id) {
            R.id.ivBack -> {
                view.navigateBack()
            }
            R.id.btnSubmit -> {
                view.findNavController()
                    .navigate(R.id.action_forgotPasswordFragment_to_otpVerificationFragment)
            }
        }
    }
}
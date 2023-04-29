package com.bipin.shopy.views.newPassword

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bipin.shopy.MainActivity
import com.bipin.shopy.R
import com.bipin.shopy.utils.navigateBack
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewPasswordVM @Inject constructor() : ViewModel() {

    var btnText: ObservableField<String>? =
        ObservableField(MainActivity.context.get()?.getString(R.string.update))

    var isUpdated: ObservableField<Boolean>? = ObservableField(false)
    fun onClick(view: View) {
        when (view.id) {
            R.id.ivBack -> view.navigateBack()

            R.id.btnUpdate -> {
                if (isUpdated?.get() == true) {
                    view.findNavController()
                        .navigate(R.id.action_newPasswordFragment_to_loginFragment)
                } else {
                    isUpdated?.set(true)
                    btnText?.set(MainActivity.context?.get()?.getString(R.string.sign_in))
                }
            }
        }
    }
}
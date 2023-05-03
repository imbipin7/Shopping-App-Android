package com.bipin.shopy.views.paymentmethod

import android.view.View
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentMethodVM  @Inject constructor(): ViewModel() {

    fun onClick(view: View){
        when(view.id){

        }
    }
}
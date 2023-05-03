package com.bipin.shopy.views.home

import android.view.View
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor() : ViewModel() {

    fun onClick(view: View){
        when(view.id){

        }
    }
}
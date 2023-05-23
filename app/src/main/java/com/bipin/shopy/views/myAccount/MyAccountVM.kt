package com.bipin.shopy.views.myAccount

import android.view.View
import androidx.lifecycle.ViewModel
import com.bipin.shopy.MainActivity
import com.bipin.shopy.R
import com.bipin.shopy.genericadapters.RecyclerAdapter
import com.bipin.shopy.model.MyAccountModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyAccountVM @Inject constructor() : ViewModel() {

    val adapter by lazy { RecyclerAdapter<MyAccountModel>(R.layout.item_my_account) }

    var optionList = listOf(
        MyAccountModel(MainActivity.context.get()?.getString(R.string.account_details)),
        MyAccountModel(MainActivity.context.get()?.getString(R.string.cards_offers)),
        MyAccountModel(MainActivity.context.get()?.getString(R.string.notifications)),
        MyAccountModel(MainActivity.context.get()?.getString(R.string.delivery_information)),
        MyAccountModel(MainActivity.context.get()?.getString(R.string.payment_info)),
        MyAccountModel(MainActivity.context.get()?.getString(R.string.language)),
        MyAccountModel(MainActivity.context.get()?.getString(R.string.privacy_settings)),
        MyAccountModel(MainActivity.context.get()?.getString(R.string.logout)),
    )

    init {
        adapter.addItems(optionList)
    }

    fun onClick(view: View) {
        when (view.id) {

        }
    }
}
package com.bipin.shopy.views.home

import android.view.View
import androidx.lifecycle.ViewModel
import com.bipin.shopy.MainActivity
import com.bipin.shopy.R
import com.bipin.shopy.genericadapters.RecyclerAdapter
import com.bipin.shopy.model.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor() : ViewModel() {

    val adapterBanner by lazy { RecyclerAdapter<Products>(R.layout.item_banner) }
    val adapterProducts by lazy { RecyclerAdapter<Products>(R.layout.item_product_horizontal) }

    var bannerList = listOf(
        Products(
            name = "Air Max 2090",
            tag = "Popular",
            img = MainActivity.context.get()?.getDrawable(R.drawable.iv_shoe1)
        ),
        Products(
            name = "Air Max 2090",
            tag = "New",
            img = MainActivity.context.get()?.getDrawable(R.drawable.iv_shoe2)
        ),
        Products(
            name = "Air Max 2090",
            tag = "Popular",
            img = MainActivity.context.get()?.getDrawable(R.drawable.iv_shoe3)
        )
    )

    fun onClick(view: View) {
        when (view.id) {

        }
    }

    init {
        adapterBanner.addItems(bannerList)
        adapterProducts.addItems(bannerList)
    }
}
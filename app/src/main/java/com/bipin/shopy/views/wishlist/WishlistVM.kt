package com.bipin.shopy.views.wishlist

import android.view.View
import androidx.lifecycle.ViewModel
import com.bipin.shopy.MainActivity
import com.bipin.shopy.R
import com.bipin.shopy.genericadapters.RecyclerAdapter
import com.bipin.shopy.model.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WishlistVM @Inject constructor() : ViewModel() {
    val adapter by lazy { RecyclerAdapter<Products>(R.layout.item_wishlist) }

    var list = listOf(
        Products(),
        Products(),
        Products()
    )

    init {
        adapter.addItems(list)
    }

    fun onClick(view: View) {
        when (view.id) {

        }
    }
}
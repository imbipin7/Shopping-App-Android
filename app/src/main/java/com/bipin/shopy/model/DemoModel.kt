package com.bipin.shopy.model

import android.graphics.drawable.Drawable
import com.bipin.shopy.genericadapters.AbstractModel

data class DemoModel(
    var name: String? = null
) :AbstractModel()

data class MyAccountModel(
    var name: String?
) : AbstractModel()

data class Products(
    var name: String?= null ,
    var tag: String?= null,
    var img: Drawable?= null,
    var price: String?= null,
    var count: Int?= null,
    var colorsCount: Int?= null
) : AbstractModel()


package com.bipin.shopy.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PageDecoration(private val space: Float) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
//        if (orientation == ORIENTATION_HORIZONTAL) {
            outRect.left = (space / 2).toInt()
            outRect.right = (space / 2).toInt()
            outRect.top = 0
            outRect.bottom = 0
//        } else {
//            outRect.top = (space / 2).toInt()
//            outRect.bottom = (space / 2).toInt()
//            outRect.left = 0
//            outRect.right = 0
//        }
    }

}
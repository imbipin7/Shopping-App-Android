package com.bipin.shopy.utils

import android.content.Context
import android.graphics.Point
import android.view.View
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.absoluteValue

internal class CardSliderTransformer(private val viewPager: ViewPager2) :
    ViewPager2.PageTransformer {
    private var minShadow = 5f
    private var baseShadow = 5f
    private var smallAlphaFactor = 0.5f
    private var smallScaleFactor = 0.5f

    private val startOffset: Float

    init {
        val windowManager = viewPager.context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val screen = Point()
        windowManager.defaultDisplay.getSize(screen)

        val horizontalPadding = viewPager.paddingEnd + viewPager.paddingStart
        startOffset = ((horizontalPadding / 2).toFloat() / (screen.x - horizontalPadding).toFloat())
    }

    override fun transformPage(page: View, position: Float) {
        if (position.isNaN())
            return

        val absPosition = (position - startOffset).absoluteValue

        if (absPosition >= 1) {
            ViewCompat.setElevation(page, minShadow)
            page.alpha = smallAlphaFactor

            if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                page.scaleY = smallScaleFactor
                page.scaleX = 1f
            } else {
                page.scaleY = 1f
                page.scaleX = smallScaleFactor
            }

        } else {
            ViewCompat.setElevation(
                page, scalingEquation(minShadow, baseShadow, absPosition)
            )

            page.alpha = scalingEquation(smallAlphaFactor, 1f, absPosition)

            if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                page.scaleY = scalingEquation(smallScaleFactor, 1f, absPosition)
                page.scaleX = 1f
            } else {
                page.scaleY = 1f
                page.scaleX = scalingEquation(smallScaleFactor, 1f, absPosition)
            }
        }
    }


    private fun scalingEquation(minValue: Float, maxValue: Float, absPosition: Float) =
        (minValue - maxValue) * absPosition + maxValue

}
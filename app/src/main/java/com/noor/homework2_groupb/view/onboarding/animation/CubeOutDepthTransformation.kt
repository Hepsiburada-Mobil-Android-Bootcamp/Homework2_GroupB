package com.noor.homework2_groupb.view.onboarding.animation

import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

class CubeOutDepthTransformation:ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            when{
                position <-1 -> page.alpha = 0f
                position <=0 -> {
                    page.alpha = 1f
                    page.pivotX = page.width.toFloat()
                    page.rotation= -90f * abs(position)
                }
                position <=1 ->{
                    page.alpha = 1f
                    page.pivotX = 0f
                    page.rotation = 90f * abs(position)
                }
                else -> page.alpha = 0f

            }
        }
            when{
                abs(position) <= 0.5f -> page.scaleY = max(0.4f , 1f - abs(position))
                abs(position) <= 1 -> page.scaleY = max(0.4f, 1f - abs(position))
                else -> Log.d("abs","Unknown")
            }
    }
}
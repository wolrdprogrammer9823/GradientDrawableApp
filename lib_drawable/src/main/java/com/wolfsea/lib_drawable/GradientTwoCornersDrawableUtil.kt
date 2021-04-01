package com.wolfsea.lib_drawable
import android.graphics.drawable.GradientDrawable
import android.view.View

/**
 *@desc GradientTwoCornersDrawableUtil
 *@author liuliheng
 *@time 2021/2/3  21:45
 **/
object GradientTwoCornersDrawableUtil {

    class Builder(private val fillColor: Int, private val view: View) {

        private var mCornerRadius: Float? = null
        private var strokeWidth: Int? = null
        private var strokeColor: Int? = null

        fun setCornerRadius(mCornerRadius: Float?): Builder {
            this.mCornerRadius = mCornerRadius
            return this
        }

        fun setStrokeWidth(strokeWidth: Int?): Builder {
            this.strokeWidth = strokeWidth
            return this
        }

        fun setStrokeColor(strokeColor: Int?): Builder {
            this.strokeColor = strokeColor
            return this
        }

        fun build() {
            val gradientDrawable = GradientDrawable().apply {
                gradientType = GradientDrawable.RECTANGLE
                setStroke(strokeWidth!!, strokeColor!!)
                setColor(fillColor)
                //The corners are ordered top-left, top-right, bottom-right, bottom-left.
                cornerRadii = floatArrayOf(
                    mCornerRadius!!,
                    mCornerRadius!!,
                    mCornerRadius!!,
                    mCornerRadius!!,
                    0F,
                    0F,
                    0F,
                    0F
                )
            }
            view.setBackgroundDrawable(gradientDrawable)
        }
    }
}
package com.wolfsea.gradientdrawableapp
import android.graphics.drawable.GradientDrawable
import android.view.View

/**
 *@desc GradientDrawableUtil工具类
 *@author liuliheng
 *@time 2021/2/3  21:45
 **/
class GradientDrawableUtil private constructor() {

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
                cornerRadius = mCornerRadius!!
            }
            view.setBackgroundDrawable(gradientDrawable)
        }
    }
}
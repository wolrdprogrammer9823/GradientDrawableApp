package com.wolfsea.gradientdrawableapp
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.View

/**
 *@desc StateListDrawable工具类
 *@author:liuliheng
 *@time: 2021/3/21 20:51
**/
object StateListDrawableUtil {

    class Builder(
        private val unpressedColor: Int,
        private val pressedColor: Int,
        private val view: View
    ) {

        private var mCornerRadius: Float? = null
        private var strokeWidth: Float? = null
        private var strokeColor: Int? = null

        fun setCornerRadius(mCornerRadius: Float?): Builder {
            this.mCornerRadius = mCornerRadius
            return this
        }

        fun setStrokeWidth(strokeWidth: Float?): Builder {
            this.strokeWidth = strokeWidth
            return this
        }

        fun setStrokeColor(strokeColor: Int?): Builder {
            this.strokeColor = strokeColor
            return this
        }

        fun build() {

            val unpressedDrawable = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                setColor(unpressedColor)
                setStroke(strokeWidth?.toInt()!!, strokeColor!!)
                cornerRadius = mCornerRadius!!
            }

            val pressedDrawable = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                setColor(pressedColor)
                setStroke(strokeWidth?.toInt()!!, strokeColor!!)
                cornerRadius = mCornerRadius!!
            }

            val stateSetDrawable = StateListDrawable().apply {
                addState(intArrayOf(android.R.attr.state_pressed), pressedDrawable)
                addState(intArrayOf(-android.R.attr.state_pressed), unpressedDrawable)
            }

            view.setBackgroundDrawable(stateSetDrawable)
        }
    }
}
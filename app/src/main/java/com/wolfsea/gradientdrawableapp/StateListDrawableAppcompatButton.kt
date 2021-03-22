package com.wolfsea.gradientdrawableapp
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

/**
 *@desc StateListDrawableAppcompatButton
 *@author liuliheng
 *@time 2021/3/22  0:07
 **/
class StateListDrawableAppcompatButton(context: Context, attributeSet: AttributeSet) :
    AppCompatButton(context, attributeSet) {

        init {
            init(context, attributeSet)
        }

    /**
     *@desc 初始化方法
     *@author:liuliheng
     *@time: 2021/3/22 0:09
    **/
    private fun init(context: Context, attributeSet: AttributeSet) {

        var unpressedColor : Int
        var pressedColor : Int

        var cornerRadius: Float
        var strokeWidth: Float
        var strokeColor: Int

        context.obtainStyledAttributes(attributeSet, R.styleable.state_list_drawable_Style).apply {

            unpressedColor = getColor(
                R.styleable.state_list_drawable_Style_state_list_unpressed_color,
                resources.getColor(android.R.color.transparent)
            )
            pressedColor = getColor(
                R.styleable.state_list_drawable_Style_state_list_pressed_color,
                resources.getColor(android.R.color.transparent)
            )
            cornerRadius = getDimension(
                    R.styleable.state_list_drawable_Style_state_list_corner_radius,
                    0F
                  )
            strokeColor = getColor(
                    R.styleable.state_list_drawable_Style_state_list_stroke_color,
                    resources.getColor(android.R.color.transparent)
            )
            strokeWidth = getDimension(
                    R.styleable.state_list_drawable_Style_state_list_stroke_width,
                    0F
            )
            recycle()
        }

        StateListDrawableUtil.Builder(
            unpressedColor,
            pressedColor,
            this@StateListDrawableAppcompatButton
        ).setCornerRadius(cornerRadius)
         .setStrokeColor(strokeColor)
         .setStrokeWidth(strokeWidth).build()
    }
}
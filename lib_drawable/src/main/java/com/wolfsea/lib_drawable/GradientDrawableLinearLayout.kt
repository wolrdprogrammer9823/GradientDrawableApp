package com.wolfsea.lib_drawable
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 *@desc GradientDrawableLinearLayout
 *@author liuliheng
 *@time 2021/2/3  22:04
 **/
class GradientDrawableLinearLayout : LinearLayout {

    constructor(context: Context?, attributeSet: AttributeSet?)
            : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context?, attributeSet: AttributeSet?, defaultInt: Int)
            : super(context, attributeSet, defaultInt) {
        init(context,attributeSet)
    }

    /**
     *@desc  初始化方法
     *@author:liuliheng
     *@time: 2021/2/3 22:05
    **/
    private fun init(context: Context?, attributeSet: AttributeSet?) {

        var fillColor: Int = context?.resources?.getColor(R.color.teal_700)!!

        var cornerRadius: Float? = null
        var strokeWidth: Float? = null
        var strokeColor: Int? = null

        context.obtainStyledAttributes(attributeSet, R.styleable.Layout_Background_Style)?.apply {

            fillColor = getColor(
                R.styleable.Layout_Background_Style_layout_fill_color,
                context.resources.getColor(R.color.teal_700)
            )

            cornerRadius =
                getDimension(R.styleable.Layout_Background_Style_layout_corner_radius, 0F)

            strokeWidth =
                getDimension(R.styleable.Layout_Background_Style_layout_stroke_width, 0F)

            strokeColor = getColor(
                R.styleable.Layout_Background_Style_layout_stroke_color,
                context.resources.getColor(android.R.color.transparent)
            )

            recycle()
        }

        GradientDrawableUtil.Builder(fillColor, this@GradientDrawableLinearLayout)
            .setCornerRadius(cornerRadius)
            .setStrokeWidth(strokeWidth = strokeWidth!!.toInt())
            .setStrokeColor(strokeColor)
            .build()
    }
}
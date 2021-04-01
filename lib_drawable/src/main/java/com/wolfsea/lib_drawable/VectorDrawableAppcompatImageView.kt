package com.wolfsea.lib_drawable
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 *@desc  VectorDrawableAppcompatImageView
 *@author liuliheng
 *@time 2021/3/21  21:06
 **/
class VectorDrawableAppcompatImageView(context: Context, attributeSet: AttributeSet) : AppCompatImageView(context, attributeSet) {

    init {
        init(context, attributeSet)
    }

    /**
     *@desc 初始化方法
     *@author:liuliheng
     *@time: 2021/3/21 21:08
     **/
    private fun init(context: Context, attributeSet: AttributeSet) {

        var tintColor: Int
        context.obtainStyledAttributes(attributeSet, R.styleable.ImageView_Background_Style).apply {
            tintColor =
                getColor(
                    R.styleable.ImageView_Background_Style_iv_fill_color,
                    context.resources.getColor(R.color.teal_200)
                )
            recycle()
        }

        VectorDrawableUtil.Builder(tintColor,this@VectorDrawableAppcompatImageView).build()
    }
}
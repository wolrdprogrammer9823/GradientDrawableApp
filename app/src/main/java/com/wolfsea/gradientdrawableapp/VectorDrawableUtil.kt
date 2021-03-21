package com.wolfsea.gradientdrawableapp
import android.graphics.drawable.VectorDrawable
import android.view.View

/**
 *@desc VectorDrawable工具类
 *@author:liuliheng
 *@time: 2021/3/21 20:51
**/
object VectorDrawableUtil {

    class Builder(private val tintColor: Int, private val view: View) {

        fun build() {
            when (view.background) {
                is VectorDrawable -> {
                    val vectorDrawable = view.background.apply {
                        setTint(tintColor)
                    }
                    view.setBackgroundDrawable(vectorDrawable)
                }
                else -> {
                    throw IllegalArgumentException("please set a VectorDrawable for background")
                }
            }
        }
    }
}
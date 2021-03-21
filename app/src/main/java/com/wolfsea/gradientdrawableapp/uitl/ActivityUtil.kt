package com.wolfsea.gradientdrawableapp.uitl
import android.app.Activity
import android.content.Intent

object ActivityUtil {

    //activity跳转
    inline fun <reified T : Activity> Activity.startActivity() {
        startActivity(Intent(this, T::class.java))
    }
}
package com.wolfsea.gradientdrawableapp
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.VectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import com.wolfsea.gradientdrawableapp.uitl.ActivityUtil
import com.wolfsea.gradientdrawableapp.uitl.ActivityUtil.startActivity
import kotlinx.android.synthetic.main.activity_entrance.*

class EntranceActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        gradient_drawable_btn.setOnClickListener(this)
        vector_drawable_btn.setOnClickListener(this)

        Log.d(TAG, "${gradient_drawable_btn.background.javaClass.simpleName}")

        when (gradient_drawable_btn.background) {
            is GradientDrawable -> {

                Log.d(TAG, "GradientDrawable")
            }

            is ShapeDrawable -> {

                Log.d(TAG, "ShapeDrawable")
            }

            is DrawerArrowDrawable -> {

                Log.d(TAG, "DrawerArrowDrawable")
            }

            is VectorDrawable -> {

                Log.d(TAG, "VectorDrawable")
            }

            is StateListDrawable -> {

                Log.d(TAG, "StateListDrawable")
            }

            else -> {

                Log.d(TAG, "others")
            }
        }
    }

    override fun onClick(view: View?) {

        when (view?.id) {
            R.id.gradient_drawable_btn -> {
                 startActivity<MainActivity>()
            }

            R.id.vector_drawable_btn -> {
                startActivity<VectorDrawableActivity>()
            }

            else -> {}
        }
    }

    companion object {
        const val TAG = "Entrance"
    }
}
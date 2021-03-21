package com.wolfsea.gradientdrawableapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wolfsea.gradientdrawableapp.uitl.ActivityUtil
import com.wolfsea.gradientdrawableapp.uitl.ActivityUtil.startActivity
import kotlinx.android.synthetic.main.activity_entrance.*

class EntranceActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        gradient_drawable_btn.setOnClickListener(this)
        vector_drawable_btn.setOnClickListener(this)
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
}
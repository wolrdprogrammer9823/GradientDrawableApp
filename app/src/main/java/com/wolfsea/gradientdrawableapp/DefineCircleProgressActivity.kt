package com.wolfsea.gradientdrawableapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_define_circle_progress.*

class DefineCircleProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_define_circle_progress)

        circle_progress_view.setProgress(50, true)
    }
}
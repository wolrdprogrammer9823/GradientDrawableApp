package com.wolfsea.gradientdrawableapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import com.wolfsea.gradientdrawableapp.uitl.logMessage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linear_layout.onClickStart {
            logMessage("onClickStart:${System.currentTimeMillis()}")
            delay(3000)
        }
    }

    @ObsoleteCoroutinesApi
    fun View.onClickStart(action: suspend () -> Unit) {
        val eventActor = actor<Unit>(Dispatchers.Main) {
            for (event in channel) {
                action()
            }
        }

        setOnClickListener {
            eventActor.offer(Unit)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //取消协程,子协程也随之取消
        cancel()
    }

}
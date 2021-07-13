package com.wolfsea.gradientdrawableapp
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.VectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import com.google.android.material.tabs.TabLayout
import com.wolfsea.gradientdrawableapp.uitl.ActivityUtil
import com.wolfsea.gradientdrawableapp.uitl.ActivityUtil.startActivity
import kotlinx.android.synthetic.main.activity_entrance.*
import kotlinx.android.synthetic.main.tab_layout_item.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EntranceActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        gradient_drawable_btn.setOnClickListener(this)
        vector_drawable_btn.setOnClickListener(this)
        define_circle_view_btn.setOnClickListener(this)

        Log.d(TAG, "${gradient_drawable_btn.background.javaClass.simpleName}")

        //Log.d(TAG, "text_drawable:${text_drawable.compoundDrawables.javaClass.simpleName}")

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

        val numbers = arrayOf("1", "2"/*, "3", "4"*/)
        val titles = arrayOf("AAA", "BBB"/*, "CCC", "DDD"*/)
        val numbersTvMap = ArrayMap<Int, TextView>()
        val titlesTvMap = ArrayMap<Int, TextView>()

        for (i in 0.until(numbers.size)) {

            val itemView = LayoutInflater.from(this).inflate(R.layout.tab_layout_item, null)

            val titlesTv = itemView.tab_title_tv
            val numberTv = itemView.tab_number_tv

            numbersTvMap[i] = numberTv
            titlesTvMap[i] = titlesTv

            titlesTv.text = titles[i]
            numberTv.text = numbers[i]

            titlesTv.setTextColor(resources.getColor(if (i == 0) R.color.teal_700 else R.color.teal_200))
            numberTv.setTextColor(resources.getColor(if (i == 0) R.color.teal_700 else R.color.teal_200))

            val tab = content_tab_layout.newTab()
            tab.customView = itemView
            content_tab_layout.addTab(tab)
        }

        content_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d(TAG, "position:${tab?.position}")
                for (i in 0.until(numbers.size)) {
                    numbersTvMap[i]?.setTextColor(resources.getColor(if (tab?.position == i) R.color.teal_700 else R.color.teal_200))
                    titlesTvMap[i]?.tab_title_tv?.setTextColor(resources.getColor(if (tab?.position == i) R.color.teal_700 else R.color.teal_200))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    override fun onClick(view: View?) {

        when (view?.id) {

            R.id.gradient_drawable_btn -> {
                 CoroutineScope(Job() + Dispatchers.Main).launch {
                     flow {
                         delay(5000)
                         emit(changeText())
                     }.flowOn(Dispatchers.IO)
                         .collect {
                             result ->
                               run {
                                  gradient_drawable_btn.text = result
                                  startActivity<MainActivity>()
                               }
                         }
                 }
            }

            R.id.vector_drawable_btn -> {
                startActivity<VectorDrawableActivity>()
            }

            R.id.define_circle_view_btn -> {
                startActivity<DefineCircleProgressActivity>()
            }

            else -> {}
        }
    }

    private fun changeText(): String = "to_gradient_drawable_new"

    companion object {
        const val TAG = "Entrance"
    }
}
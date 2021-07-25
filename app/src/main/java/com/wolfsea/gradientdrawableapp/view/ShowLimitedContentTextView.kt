package com.wolfsea.gradientdrawableapp.view
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView

/**
 *@desc   显示有限内容的AppCompatTextView
 *@author liuliheng
 *@time 2021/7/19  23:03
 **/
class ShowLimitedContentTextView(context: Context, attributeSet: AttributeSet) :
    AppCompatTextView(context, attributeSet) {

    init {
        setOnClickListener {
           AlertDialog.Builder(context)
                     .setMessage(text)
               .setPositiveButton("确定") { dialog, _ ->
                   dialog.dismiss()
               }
               .create().show()
        }
    }

}
package com.wolfsea.gradientdrawableapp.view
import android.text.Editable
import android.text.TextWatcher

/**
 *@desc 简单的TextWatcher
 *@author liuliheng
 *@time 2021/7/26  23:21
 **/
abstract class SimpleTextWatcher : TextWatcher {

    override fun beforeTextChanged(sequence: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(sequence: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable?) {}

}
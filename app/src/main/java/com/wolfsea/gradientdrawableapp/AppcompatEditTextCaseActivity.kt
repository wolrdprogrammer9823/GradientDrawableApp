package com.wolfsea.gradientdrawableapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import com.wolfsea.gradientdrawableapp.view.SimpleTextWatcher
import kotlinx.android.synthetic.main.activity_appcompat_edit_text_case.*
import java.util.regex.Pattern

class AppcompatEditTextCaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appcompat_edit_text_case)

        init()
    }

    /**
     *@desc 初始化方法
     *@author:liuliheng
     *@time: 2021/7/26 23:43
    **/
    private fun init() {

        content_text.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(editable: Editable?) {

                super.afterTextChanged(editable)
                mEditable = editable
                val content = editable.toString()
                val contentNotEmpty = !TextUtils.isEmpty(content)
                if (contentNotEmpty) {

                    val pattern = Pattern.compile(NUMBER_REGEX)
                    val matcher = pattern.matcher(content)
                    val notMatches = !matcher.matches()
                    if (notMatches) {
                        editable?.apply {
                            delete(this.length - 1, this.length)
                        }
                    }
                }
            }
        })

        clear_btn.setOnClickListener {
            mEditable?.apply {
                delete(0, this.length)
            }
        }
    }

    companion object {
        const val NUMBER_REGEX = "^[1-9]\\d{0,3}"
    }

    private var mEditable: Editable? = null
}
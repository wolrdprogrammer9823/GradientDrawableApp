package com.wolfsea.lib_drawable
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

/**
 *@desc IconTextConstraintLayout
 *@author:liuliheng
 *@time: 2021/4/1 23:37
**/
class IconTextRelativeLayout : RelativeLayout {

    constructor(context: Context, attributeSet: AttributeSet?)
            : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defaultInt: Int)
            : super(context, attributeSet, defaultInt) {
        init(context,attributeSet)
    }

    /**
     *@desc 初始化方法
     *@author:liuliheng
     *@time: 2021/4/2 1:11
    **/
    private fun init(context: Context, attributeSet: AttributeSet?) {

        //图标
        var layoutIcon : Int?
        //图标-宽
        var layoutIconWidth : Int?
        //图标-高
        var layoutIconHeight : Int?
        //文本
        var layoutText : String?
        //文本-宽
        var layoutTextWidth : Int?
        //文本-高
        var layoutTextHeight : Int?
        //文本-颜色
        var layoutTextColor : Int?
        //文本-大小
        var layoutTextSize : Float?
        //图标文本间距
        var layoutIconTextDimen : Float?
        //图标文本方向
        var layoutIconTextDirection : Int?

        context.obtainStyledAttributes(attributeSet, R.styleable.Layout_Icon_Text_Style).apply {

            layoutIcon = getResourceId(R.styleable.Layout_Icon_Text_Style_layout_icon, -1)

            layoutIconWidth = getDimension(R.styleable.Layout_Icon_Text_Style_layout_icon_width, LayoutParams.WRAP_CONTENT.toFloat()).toInt()

            layoutIconHeight = getDimension(R.styleable.Layout_Icon_Text_Style_layout_icon_height, LayoutParams.WRAP_CONTENT.toFloat()).toInt()

            layoutText = getString(R.styleable.Layout_Icon_Text_Style_layout_text)

            layoutTextWidth = getDimension(R.styleable.Layout_Icon_Text_Style_layout_text_width, LayoutParams.WRAP_CONTENT.toFloat()).toInt()

            layoutTextHeight = getDimension(R.styleable.Layout_Icon_Text_Style_layout_text_height, LayoutParams.WRAP_CONTENT.toFloat()).toInt()

            layoutTextColor = getColor(
                R.styleable.Layout_Icon_Text_Style_layout_text_color,
                context.resources.getColor(R.color.purple_200)
            )

            layoutTextSize = getDimension(
                R.styleable.Layout_Icon_Text_Style_layout_text_size,
                0F
            )

            layoutText = getString(R.styleable.Layout_Icon_Text_Style_layout_text)

            layoutIconTextDimen = getDimension(R.styleable.Layout_Icon_Text_Style_layout_icon_text_dimen, 0F)

            layoutIconTextDirection = getInt(R.styleable.Layout_Icon_Text_Style_layout_icon_text_direction, 0)

            recycle()
        }

        val imageView = ImageView(context, attributeSet)
        imageView.id = R.id.layout_icon
        imageView.setBackgroundResource(layoutIcon!!)
        val imageViewLayoutParams = LayoutParams(
            layoutIconWidth!!,
            layoutIconHeight!!
        )

        layoutIconTextDirection?.let {
            when (it) {
                0 -> {
                    imageViewLayoutParams.addRule(CENTER_VERTICAL)
                    imageView.layoutParams = imageViewLayoutParams
                }
                1 -> {
                    imageViewLayoutParams.addRule(ALIGN_TOP)
                    imageView.layoutParams = imageViewLayoutParams
                }
            }
        }

        val textView = TextView(context, attributeSet)
        textView.id = R.id.layout_text
        textView.text = layoutText
        textView.setTextColor(layoutTextColor!!)
        textView.textSize = layoutTextSize!!.div(resources.displayMetrics.density)

        val textViewLayoutParams = LayoutParams(
            layoutTextWidth!!,
            layoutTextHeight!!
        )

        Log.d(TAG, "layoutIconTextDirection->${layoutIconTextDirection}")

        layoutIconTextDirection?.let {
            when (it) {
                0 -> {
                    textViewLayoutParams.addRule(RIGHT_OF, R.id.layout_icon)
                    textViewLayoutParams.leftMargin = layoutIconTextDimen!!.toInt()
                    textViewLayoutParams.addRule(CENTER_VERTICAL)
                    textView.layoutParams = textViewLayoutParams
                }

                1 -> {
                    textViewLayoutParams.addRule(BELOW, R.id.layout_icon)
                    textViewLayoutParams.topMargin = layoutIconTextDimen!!.toInt()
                    textViewLayoutParams.addRule(CENTER_HORIZONTAL)
                    textView.layoutParams = textViewLayoutParams
                }
            }
        }

        addView(imageView)
        addView(textView)
    }

    companion object {
        const val TAG = "IconTextLayout"
    }

}
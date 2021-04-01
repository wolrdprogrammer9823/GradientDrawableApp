package com.wolfsea.lib_drawable
import android.content.Context
import android.util.AttributeSet
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

        var layoutIcon : Int?
        var layoutText : String?
        var layoutTextColor : Int?
        var layoutTextSize : Float?
        var layoutDimen : Float?
        var layoutDirection : Int?

        context.obtainStyledAttributes(attributeSet, R.styleable.Layout_Icon_Text_Style).apply {

            layoutIcon = getResourceId(R.styleable.Layout_Icon_Text_Style_layout_icon, -1)

            layoutText = getString(R.styleable.Layout_Icon_Text_Style_layout_text)

            layoutTextColor = getColor(
                R.styleable.Layout_Icon_Text_Style_layout_text_color,
                context.resources.getColor(R.color.purple_200)
            )

            layoutTextSize = getDimension(
                R.styleable.Layout_Icon_Text_Style_layout_text_size,
                0F
            )

            layoutText = getString(R.styleable.Layout_Icon_Text_Style_layout_text)

            layoutDimen = getDimension(R.styleable.Layout_Icon_Text_Style_layout_dimen, 0F)

            layoutDirection = getInt(R.styleable.Layout_Icon_Text_Style_layout_direction, 0)

            recycle()
        }

        val imageView = ImageView(context, attributeSet)
        imageView.id = R.id.layout_icon
        imageView.setBackgroundResource(layoutIcon!!)
        val imageViewLayoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        imageViewLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL)
        imageView.layoutParams = imageViewLayoutParams

        val textView = TextView(context, attributeSet)
        textView.id = R.id.layout_text
        textView.text = layoutText
        textView.setTextColor(layoutTextColor!!)
        textView.textSize = layoutTextSize!!
        val textViewLayoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )

        textViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.layout_icon)
        layoutDirection?.let {
            when (it) {
                0 -> {
                    textViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.layout_icon)
                }
                1 -> {
                    textViewLayoutParams.addRule(RelativeLayout.BELOW, R.id.layout_icon)
                }
            }
        }

        textViewLayoutParams.leftMargin = layoutDimen!!.toInt()
        textViewLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL)
        textView.layoutParams = textViewLayoutParams

        addView(imageView)
        addView(textView)
    }
}
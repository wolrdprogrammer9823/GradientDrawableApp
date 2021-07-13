package com.wolfsea.gradientdrawableapp.view
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.OvershootInterpolator
import com.wolfsea.gradientdrawableapp.R

/**
 *@desc  圆形进度条View
 *@author liuliheng
 *@time 2021/7/12  23:36
 **/
class CircleProgressView : View {

    constructor(context: Context, attributeSet: AttributeSet) : this(context, attributeSet, -1)

    constructor(context: Context,
                attributeSet: AttributeSet,
                defStyleAttr: Int
    ) : super(context, attributeSet,defStyleAttr) {
        initData(context, attributeSet, defStyleAttr)
    }

    private var currentValue = 0
    //扫过的角度
    private var swipeAngle = 0F

    //底部颜色
    private var firstColor = 0
    //进度条颜色
    private var secondColor = 0

    //圆环的宽度
    private var circleWidth = 0

    //圆弧的画笔
    private var circlePaint: Paint? = null
    //画文字的画笔
    private var textPaint: Paint? = null

    //RectF
    private lateinit var oval: RectF
    //Rect
    private lateinit var bounds: Rect

    //线性渐变
    private var linearGradient: LinearGradient? = null


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(
            Math.min(measureWidth, measureHeight),
            Math.min(measureHeight, measureWidth)
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val center = width shr 1
        val radius = center - circleWidth shr 1
        drawCircle(canvas, center, radius)
        drawText(canvas, center)
    }

    /**
     *@desc 绘制圆
     *@author:liuliheng
     *@time: 2021/7/13 0:11
     **/
    private fun drawCircle(canvas: Canvas?, center: Int, radius: Int) {

        circlePaint?.apply {
            shader = null
            color = firstColor
            style = Paint.Style.STROKE
        }
        //绘制底部的空心圆
        canvas?.apply {
            drawCircle(center.toFloat(), center.toFloat(), radius.toFloat(), circlePaint!!)
        }

        oval.set(
            (center - radius).toFloat(),
            (center - radius).toFloat(),
            (center + radius).toFloat(),
            (center + radius).toFloat()
        )

        if (linearGradient == null) {
            linearGradient = LinearGradient(
                circleWidth.toFloat(),
                circleWidth.toFloat(),
                (measuredWidth - circleWidth).toFloat(),
                (measuredHeight - circleWidth).toFloat(),
                COLOR_ARRAY,
                null,
                Shader.TileMode.MIRROR
            )
        }

        circlePaint?.apply {
            shader = linearGradient
            setShadowLayer(10F, 10F, 10F, Color.RED)
            color = secondColor
            //把每段圆弧改成圆角的
            strokeCap = Paint.Cap.ROUND
        }

        swipeAngle = currentValue * 360F / MAX_VALUE * 1.0F

        canvas?.apply {
            drawArc(oval, -90F, swipeAngle, false, circlePaint!!)
        }
    }

    /**
     *@desc 绘制文本
     *@author:liuliheng
     *@time: 2021/7/13 0:11
    **/
    private fun drawText(canvas: Canvas?, center: Int) {

        val progress = currentValue * 100F / MAX_VALUE * 1.0F

        val percent = "${String.format("%.1f", progress)}%"

        textPaint?.apply {
            textAlign = Paint.Align.CENTER
            color = Color.BLACK
            textSize = 40F
            strokeWidth = 0F
            getTextBounds(percent, 0, percent.length, bounds)
        }

        val fontMetrics = textPaint?.fontMetrics
        val baseLine = center + (fontMetrics?.bottom!! - fontMetrics.top) / 2.0F - fontMetrics.bottom
        canvas?.apply {
            drawText(percent, center.toFloat(), baseLine, textPaint!!)
        }
    }

    fun updateCircleWidth(width: Int) {

        this.circleWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            width.toFloat(),
            resources.displayMetrics
        ).toInt()

        invalidate()
    }

    fun updateFirstColor(firstColor: Int) {

        this.firstColor = firstColor

        circlePaint?.apply {
            color = firstColor
        }

        invalidate()
    }

    fun updateSecondColor(secondColor: Int) {

        this.secondColor = secondColor

        circlePaint?.apply {
            color = secondColor
        }

        invalidate()
    }

    fun setProgress(progress: Int) {

        var percent = progress * MAX_VALUE / 100

        if (percent < 0) {

            percent = 0
        }

        if (percent > 100) {

            percent = 100
        }

        this.currentValue = percent
        invalidate()
    }

    fun setProgress(progress: Int, useAnimation: Boolean) {

        var percent = progress * MAX_VALUE / 100

        if (percent < 0) {

            percent = 0
        }

        if (percent > 100) {

            percent = 100
        }

        if (useAnimation) {

            val animator = ValueAnimator.ofInt(0, percent)

            animator.apply {
               addUpdateListener { animation ->
                   currentValue = animation?.animatedValue!! as Int
                   invalidate()
               }
               interpolator = OvershootInterpolator()
               duration = 3000
               start()
            }

        } else {

            setProgress(percent)
        }
    }

    /**
     *@desc 初始化数据
     *@author:liuliheng
     *@time: 2021/7/12 23:46
     **/
    private fun initData(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) {

        val typedArray =
            context.obtainStyledAttributes(
                attributeSet,
                R.styleable.CircleProgressBar,
                defStyleAttr,
                0
            )

        typedArray.apply {
            val indexCount = indexCount
            for (i in 0..indexCount) {
                when (this.getIndex(i)) {
                    R.styleable.CircleProgressBar_firstColor -> {

                        firstColor = getColor(this.getIndex(i), Color.LTGRAY)
                    }

                    R.styleable.CircleProgressBar_secondColor -> {

                       secondColor = getColor(this.getIndex(i), Color.BLUE)
                    }

                    R.styleable.CircleProgressBar_circleWidth -> {

                        circleWidth =
                            getDimensionPixelSize(
                                this.getIndex(i),
                                TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    6F,
                                    context.resources.displayMetrics).toInt()
                            )
                    }

                    else -> {}
                }
            }
            recycle()
        }

        circlePaint = Paint()
        circlePaint?.isAntiAlias = true
        //防抖动
        circlePaint?.isDither = true
        circlePaint?.strokeWidth = circleWidth.toFloat()

        textPaint = Paint()
        textPaint?.isAntiAlias = true
        //防抖动
        textPaint?.isDither = true

        //圆的外界正方形
        oval = RectF()
        //文字边框
        bounds = Rect()
    }


    companion object {
        //最大值
        const val MAX_VALUE = 100

        //颜色值数组
        val COLOR_ARRAY = intArrayOf(
            Color.parseColor("#FF03DAC5"),
            Color.parseColor("#FF018786")
        )
    }

}
package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.Dimension
import ru.skillbranch.devintensive.R

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    companion object {
        private const val DEFAULT_BORDER_COLOR: Int = Color.WHITE
        private const val DEFAULT_BORDER_WIDTH = 2f
    }

    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH


    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_BORDER_COLOR)
            borderWidth = a.getDimension(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_BORDER_WIDTH)
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paint = Paint()
        paint.strokeWidth = borderWidth
        paint.setColor(borderColor)
        paint.style = Paint.Style.STROKE
        canvas?.drawCircle(width / 2f, height / 2f, height / 2f, paint)
    }

    private fun getBorderWidth(): Int {
        return borderWidth.toInt()
    }

    private fun setBorderWidth(dp: Int) {
        borderWidth = dp.toFloat()
    }

    private fun getBorderColor():Int{
        return borderColor
    }

    private fun setBorderColor(hex:String){
        borderColor = Integer.decode(hex)
    }

    private fun setBorderColor(colorId: Int){
        borderColor = colorId
    }

}
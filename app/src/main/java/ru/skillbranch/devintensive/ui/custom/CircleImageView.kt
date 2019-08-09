package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
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
    private val paint = Paint()
    private var hasInitials : Boolean= true

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

        paint.strokeWidth = borderWidth
        paint.setColor(borderColor)
        paint.style = Paint.Style.STROKE
        canvas?.drawCircle(width / 2f, height / 2f, height / 2f, paint)
        if(hasInitials){

            drawInitials("OK", canvas)
        }

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

    private fun drawInitials(initials: String, canvas: Canvas?){
        Log.d("M_Activity", "initials $initials")
        paint.style = Paint.Style.FILL
        paint.setColor(R.attr.colorAccent)
        canvas?.drawCircle(width / 2f, height / 2f, height / 2f, paint)
        paint.setColor(Color.WHITE);
        paint.setTextSize(height/2f);
        paint.setStyle(Paint.Style.FILL);
       canvas?.drawText(initials, width/6f, height/1.5f, paint);
    }
}
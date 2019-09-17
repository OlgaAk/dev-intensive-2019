package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.ImageView

class AvatarImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    private var avatarSize: Int = 0
    private var rect: Rect = Rect()
    private var pathR: Path = Path()
    private lateinit var paintText: Paint
    private lateinit var paintBorder: Paint
    private var borderWidth: Float = DEFAULT_BORDER_WIDTH
    private var borderColor: Int = DEFAULT_BORDER_COLOR
    private var initials: String? = null
    private val bgColors = arrayOf(
        "#7BC862",
        "#E17076"
    )
    companion object{
        private const val DEFAULT_BORDER_WIDTH = 2f
        private const val DEFAULT_BORDER_COLOR = android.graphics.Color.GREEN // written by me
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paintBorder = Paint()
        paintBorder.setColor(borderColor)
//        paintBorder.setStyle(Paint.Style.STROKE)
        canvas?.translate(getWidth()/2f,getHeight()/2f);
        paintText = Paint()
        paintText.setColor(android.graphics.Color.WHITE)
        paintText.textSize = 50f;
        initials = "??"
        var textsize = paintText.measureText(initials)
        var bounds = Rect();
        paintText.getTextBounds(initials, 0, initials!!.length, bounds);
        var height = bounds.height();


        canvas?.drawCircle(0f, 0f, width/2f, paintBorder);
        canvas?.drawText(initials!!, 0f-textsize/2, 0f+height/3, paintText);
    }

}
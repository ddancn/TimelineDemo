package com.ddancn.timelinedemo.timeline.thirdvertimeline

import android.content.Context
import android.graphics.*
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author ddan.zhuang
 * @date 2020/2/29
 *
 */
class CustomTimeline<T>(private val context: Context) : ThirdVerTimeline<T>() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var radius = 10f
    var drawableRes: Int = 0

    override fun drawNode(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State,
        xPosition: Float,
        item: T,
        itemView: View,
        adapterPosition: Int
    ) {
        paint.color = color(item)
        // 画图
        if (adapterPosition == 0) {
            val bitmap = BitmapFactory.decodeResource(context.resources, drawableRes)
            val src = Rect(0, 0, bitmap.width, bitmap.height)

            val left = xPosition - nodeWidth / 2
            val top = (itemView.top + offset).toFloat()
            val dst = RectF(
                left,
                top,
                left + nodeWidth,
                top + nodeHeight
            )
            c.drawBitmap(
                bitmap,
                src,
                dst,
                Paint()
            )
        } else {
            c.drawCircle(
                xPosition,
                itemView.top + radius + offset.toFloat(),
                radius,
                paint
            )

        }
    }
}
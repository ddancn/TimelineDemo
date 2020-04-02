package com.ddancn.timelinedemo.timeline.finalvertimeline

import android.content.Context
import android.graphics.*
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author ddan.zhuang
 * @date 2020/4/2
 *
 */
class CustomTimelineDecoration<T>(private val context: Context) : BaseTimelineDecoration<T>() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var radius = 8f
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
        paint.color = color(item, adapterPosition)
        // 画图
        if (adapterPosition == 0) {
            val bitmap = BitmapFactory.decodeResource(context.resources, drawableRes)
            val src = Rect(0, 0, bitmap.width, bitmap.height)

            val left = xPosition - nodeWidth(item, adapterPosition) / 2
            val top = (itemView.top + offset).toFloat()
            val dst = RectF(
                left,
                top,
                left + nodeWidth(item, adapterPosition),
                top + nodeHeight(item, adapterPosition)
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
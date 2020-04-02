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
class DrawableTimelineDecoration<T>(private val context: Context) : BaseTimelineDecoration<T>() {

    lateinit var drawableRes: (item: T, position: Int) -> Int

    override fun drawNode(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State,
        xPosition: Float,
        item: T,
        itemView: View,
        adapterPosition: Int
    ) {
        val bitmap =
            BitmapFactory.decodeResource(context.resources, drawableRes(item, adapterPosition))
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
    }
}
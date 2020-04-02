package com.ddancn.timelinedemo.timeline.thirdvertimeline

import android.content.Context
import android.graphics.*
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * @author ddan.zhuang
 * @date 2020/2/28
 *
 */
class PicTimeline<T>(private val context: Context) : ThirdVerTimeline<T>() {

    lateinit var drawableRes: (item: T) -> Int

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
            BitmapFactory.decodeResource(context.resources, drawableRes(item))
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
    }
}
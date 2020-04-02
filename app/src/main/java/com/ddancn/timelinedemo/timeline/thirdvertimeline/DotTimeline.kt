package com.ddancn.timelinedemo.timeline.thirdvertimeline

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author ddan.zhuang
 * @date 2020/4/1
 *
 */
class DotTimeline<T> : ThirdVerTimeline<T>() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    val radius = 10f
    var dotColor: (item: T) -> Int = { _ -> Color.GRAY }

    override fun drawNode(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State,
        xPosition: Float,
        item: T,
        itemView: View,
        adapterPosition: Int
    ) {
        // 画圆
        paint.color = dotColor(item)
        c.drawCircle(xPosition, itemView.top + offset + radius, radius, paint)
    }

}
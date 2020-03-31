package com.ddancn.timelinedemo.timeline

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author ddancn
 * @date 2020/3/30
 *
 */
class FirstVerTimeline : RecyclerView.ItemDecoration() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var radius = 8f
    var offset = 15

    var paddingLeft = 15
    var paddingRight = 15

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val count = parent.childCount
        for (i in 0 until count) {
            // 获取当前的itemView
            val itemView = parent.getChildAt(i)
            // 整个轴线的x坐标都是相同的
            val xPosition = radius + paddingLeft

            // 画上线。第一个item不画
            if (i != 0) {
                c.drawLine(
                    xPosition,
                    itemView.top.toFloat(),
                    xPosition,
                    itemView.top.toFloat() + offset,
                    paint
                )
            }
            // 画下线。最后一个item不画
            if (i != count - 1) {
                c.drawLine(
                    xPosition,
                    itemView.top + offset + radius * 2,
                    xPosition,
                    itemView.bottom.toFloat(),
                    paint
                )
            }
            c.drawCircle(xPosition, itemView.top + offset + radius, radius, paint)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = paddingLeft + paddingRight + radius.toInt() * 2
    }
}
package com.ddancn.timelinedemo.timeline.thirdvertimeline

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author ddancn
 * @date 2020/4/1
 *
 */
abstract class ThirdVerTimeline<T> : RecyclerView.ItemDecoration() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var data: List<T> = ArrayList()

    var offset = 15

    var paddingLeft = 15
    var paddingRight = 15

    var color: (item: T) -> Int = { _ -> Color.GRAY }

    var nodeWidth = 20
    var nodeHeight = 20

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val count = parent.childCount
        for (i in 0 until count) {
            // 获取当前的itemView
            val itemView = parent.getChildAt(i)
            // 当前项的真正位置
            val adapterPosition = parent.getChildAdapterPosition(itemView)
            // 当前项的数据源
            val item = data[adapterPosition]
            // 整个轴线的x坐标都是相同的
            val xPosition = (nodeWidth / 2 + paddingLeft).toFloat()

            // 画上线。第一个item不画
            if (adapterPosition != 0) {
                // 设置上线的颜色
                paint.color = color(data[adapterPosition - 1])
                c.drawLine(
                    xPosition,
                    itemView.top.toFloat(),
                    xPosition,
                    itemView.top.toFloat() + offset,
                    paint
                )
            }

            // 设置圆和下线的颜色
            paint.color = color(item)
            // 画下线。最后一个item不画
            if (adapterPosition != data.size - 1) {
                c.drawLine(
                    xPosition,
                    (itemView.top + offset + nodeHeight).toFloat(),
                    xPosition,
                    itemView.bottom.toFloat(),
                    paint
                )
            }
            drawNode(c, parent, state, xPosition, item, itemView, adapterPosition)
        }
    }

    protected abstract fun drawNode(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State,
        xPosition: Float,
        item: T,
        itemView: View,
        adapterPosition: Int
    )

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = paddingLeft + paddingRight + nodeWidth
    }
}
package com.ddancn.timelinedemo.timeline.finalvertimeline

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author ddan.zhuang
 * @date 2020/4/2
 * 时间线
 */
abstract class BaseTimelineDecoration<T> : RecyclerView.ItemDecoration() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 数据源
    var data: List<T> = ArrayList()

    // 结点距离顶部的偏移量
    var offset = 15
    // item间距
    var itemMargin = 15
    // 列表的左右padding
    var paddingLeft = 15
    var paddingRight = 15
    // 时间线在列表的位置
    var direction: Direction = Direction.LEFT
    // 轴线宽度
    var lineWidth = 2f
        set(value) {
            field = value
            paint.strokeWidth = value
        }

    // 轴线颜色
    var color: (item: T, position: Int) -> Int = { _, _ -> Color.GRAY }
    // 结点宽度
    var nodeWidth: (item: T, position: Int) -> Int = { _, _ -> 15 }
    // 结点高度
    var nodeHeight: (item: T, position: Int) -> Int = { _, _ -> 15 }
    // 最大宽度
    var maxWidth = 15

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val count = parent.childCount
        for (i in 0 until count) {
            val itemView = parent.getChildAt(i)
            val xPosition = getLineX(itemView)
            val adapterPosition = parent.getChildAdapterPosition(itemView)
            val item = data[adapterPosition]

            // 画上线。第一个item不画
            if (adapterPosition != 0) {
                // 设置上一个item颜色
                paint.color = color(data[adapterPosition - 1], adapterPosition - 1)
                c.drawLine(
                    xPosition,
                    itemView.top.toFloat(),
                    xPosition,
                    itemView.top + offset.toFloat(),
                    paint
                )
            }
            // 设置这一个item颜色
            paint.color = color(item, adapterPosition)
            // 画下线。最后一个item不画
            if (adapterPosition != data.size - 1) {
                c.drawLine(
                    xPosition,
                    itemView.top + nodeHeight(item, adapterPosition) + offset.toFloat(),
                    xPosition,
                    itemView.bottom + itemMargin.toFloat(),
                    paint
                )
            }
            drawNode(c, parent, state, xPosition, item, itemView, adapterPosition)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        // 设置整个decor的宽度
        val width = paddingLeft + maxWidth + paddingRight
        if (direction == Direction.LEFT) {
            outRect.left = width
        } else {
            outRect.right = width
        }
        // 设置bottom间距，最后一个item不设置
        val cur = parent.getChildAdapterPosition(view)
        val count = state.itemCount
        if (cur != count - 1) {
            outRect.bottom = itemMargin
        }
    }

    private fun getLineX(view: View): Float =
        if (direction == Direction.LEFT) {
            (maxWidth / 2 + paddingLeft).toFloat()
        } else {
            view.right.toFloat() + paddingLeft + maxWidth / 2
        }

    /**
     * 子类负责绘制结点
     */
    protected abstract fun drawNode(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State,
        xPosition: Float,
        item: T,
        itemView: View,
        adapterPosition: Int
    )

    enum class Direction {
        LEFT,
        RIGHT
    }

}
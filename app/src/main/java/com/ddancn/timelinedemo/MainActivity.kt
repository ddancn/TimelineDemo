package com.ddancn.timelinedemo

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ddancn.timelinedemo.timeline.FirstVerTimeline
import com.ddancn.timelinedemo.timeline.SecondVerTimeline
import com.ddancn.timelinedemo.timeline.finalvertimeline.BaseTimelineDecoration
import com.ddancn.timelinedemo.timeline.finalvertimeline.CustomTimelineDecoration
import com.ddancn.timelinedemo.timeline.finalvertimeline.DotTimelineDecoration
import com.ddancn.timelinedemo.timeline.thirdvertimeline.CustomTimeline
import com.ddancn.timelinedemo.timeline.thirdvertimeline.DotTimeline
import com.ddancn.timelinedemo.timeline.thirdvertimeline.PicTimeline
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val records = ArrayList<Record>()
        val adapter = RecordAdapter()
        records.add(Record(1, "2020-03-31", "第一步已完成"))
        records.add(Record(2, "2020-03-31", "第二步进行中"))
        records.add(Record(3, "2020-03-31", "第三步未开始"))
        adapter.data = records

        val colorStrategy = { item: Record ->
            when (item.status) {
                1 -> resources.getColor(R.color.colorPrimaryDark)
                2 -> resources.getColor(R.color.colorPrimary)
                3 -> resources.getColor(R.color.colorAccent)
                else -> resources.getColor(R.color.colorPrimaryDark)
            }
        }

        // 第一版，圆点
        rv_timeline1.adapter = adapter
        rv_timeline1.addItemDecoration(FirstVerTimeline())

        // 第二版，颜色圆点
        rv_timeline2.adapter = adapter
        val secondVerTimeline = SecondVerTimeline<Record>()
        secondVerTimeline.data = records
        secondVerTimeline.color = colorStrategy
        rv_timeline2.addItemDecoration(secondVerTimeline)

        // 第三版，颜色圆点
        rv_timeline3.adapter = adapter
        val dotTimeline = DotTimeline<Record>()
        dotTimeline.data = records
        dotTimeline.color = colorStrategy
        dotTimeline.dotColor = colorStrategy
        rv_timeline3.addItemDecoration(dotTimeline)

        // 第四版，图片
        rv_timeline4.adapter = adapter
        val picTimeline = PicTimeline<Record>(this)
        picTimeline.data = records
        picTimeline.color = colorStrategy
        picTimeline.drawableRes = { item ->
            when (item.status) {
                1 -> R.drawable.ic_checked
                else -> R.drawable.ic_uncheck
            }
        }
        rv_timeline4.addItemDecoration(picTimeline)

        // 第四版，颜色圆点+图片
        rv_timeline5.adapter = adapter
        val customTimeline = CustomTimeline<Record>(this)
        customTimeline.data = records
        customTimeline.color = colorStrategy
        customTimeline.drawableRes = R.drawable.ic_checked
        rv_timeline5.addItemDecoration(customTimeline)

        val colorStrategy2 = { item: Record, position: Int ->
            when (item.status) {
                1 -> resources.getColor(R.color.colorPrimaryDark)
                2 -> resources.getColor(R.color.colorPrimary)
                3 -> resources.getColor(R.color.colorAccent)
                else -> resources.getColor(R.color.colorPrimaryDark)
            }
        }

        // 最终版1，颜色圆点
        rv_timeline6.adapter = adapter
        val dotTimeline2 = DotTimelineDecoration<Record>()
        dotTimeline2.data = records
        dotTimeline2.color = colorStrategy2
        dotTimeline2.dotColor = colorStrategy2
        dotTimeline2.direction = BaseTimelineDecoration.Direction.RIGHT
        rv_timeline6.addItemDecoration(dotTimeline2)

        // 最终版2，空心圆点
        rv_timeline7.adapter = adapter
        val hollowTimeline = DotTimelineDecoration<Record>()
        hollowTimeline.data = records
        hollowTimeline.color = colorStrategy2
        hollowTimeline.strokeColor = colorStrategy2
        hollowTimeline.nodeType = DotTimelineDecoration.NodeType.STROKE
        hollowTimeline.direction = BaseTimelineDecoration.Direction.RIGHT
        hollowTimeline.lineWidth = 3f
        rv_timeline7.addItemDecoration(hollowTimeline)

        // 最终版3，颜色圆点+图片+大小设置
        rv_timeline8.adapter = adapter
        val finalTimeline = CustomTimelineDecoration<Record>(this)
        finalTimeline.data = records
        finalTimeline.color = colorStrategy2
        finalTimeline.drawableRes = R.drawable.ic_checked
        finalTimeline.direction = BaseTimelineDecoration.Direction.RIGHT
        finalTimeline.nodeWidth = { _, adapterPosition -> if (adapterPosition == 0) 30 else 16 }
        finalTimeline.nodeHeight = { _, adapterPosition -> if (adapterPosition == 0) 30 else 16 }
        finalTimeline.maxWidth = 30
        rv_timeline8.addItemDecoration(finalTimeline)

    }
}

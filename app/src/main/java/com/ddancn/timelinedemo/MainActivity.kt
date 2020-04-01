package com.ddancn.timelinedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ddancn.timelinedemo.timeline.FirstVerTimeline
import com.ddancn.timelinedemo.timeline.SecondVerTimeline
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

        // 第一个rv
        rv_timeline1.adapter = adapter
        rv_timeline1.addItemDecoration(FirstVerTimeline())

        // 第二个rv
        rv_timeline2.adapter = adapter
        val secondVerTimeline =
            SecondVerTimeline<Record>()
        secondVerTimeline.data = records
        secondVerTimeline.color = { item ->
            when (item.status) {
                1 -> resources.getColor(R.color.colorPrimaryDark)
                2 -> resources.getColor(R.color.colorPrimary)
                3 -> resources.getColor(R.color.colorAccent)
                else -> resources.getColor(R.color.colorPrimaryDark)
            }
        }
        rv_timeline2.addItemDecoration(secondVerTimeline)
    }
}

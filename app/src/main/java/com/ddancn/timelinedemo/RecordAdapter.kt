package com.ddancn.timelinedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author ddancn
 * @date 2020/3/30
 *
 */
class RecordAdapter : RecyclerView.Adapter<RecordAdapter.ViewHolder>() {

    var data = ArrayList<Record>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvContent: TextView = view.findViewById(R.id.tv_content)
        var tvTime: TextView = view.findViewById(R.id.tv_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_timeline_record, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = data[position]
        holder.tvContent.text = record.content
        holder.tvTime.text = record.time
    }

    override fun getItemCount(): Int = data.size
}
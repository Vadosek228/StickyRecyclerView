package com.example.stickyrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(
    val context: Context,
    val dataList: ArrayList<HashMap<String, String>>
) : RecyclerView.Adapter<DataAdapter.DataHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false)
        val dataHolder = DataHolder(view)
        return dataHolder
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val dataMap: HashMap<String, String> = dataList[position]
        val wordMeaning = dataMap.get("Desc1")
        val wordExample = dataMap.get("Desc2")
        holder.tvMeaning.setText(wordMeaning)
        holder.tvExample.setText(wordExample)
    }

    class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMeaning: TextView = itemView.findViewById(R.id.text_view_item_main_title)
        val tvExample: TextView = itemView.findViewById(R.id.text_view_item_main_text)
    }
}
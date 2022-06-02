package com.ildango.capstone.mypages.myalarmlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ildango.capstone.R
import com.ildango.capstone.data.model.MyAlarmItem
import retrofit2.Response

class MyAlarmListAdapter() : RecyclerView.Adapter<MyAlarmListAdapter.MyAlarmListViewHolder>() {

    private val items = mutableListOf<MyAlarmItem>()

    fun setItems(items: List<MyAlarmItem>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAlarmListViewHolder {
        return MyAlarmListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MyAlarmListViewHolder, position: Int) {
        items.let { item ->
            with(holder) {
                tv_keyword.text = item.get(position).itemName
                tv_price.text = "${item.get(position).targetPrice}원"
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyAlarmListViewHolder constructor(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_alarm_listview, parent, false)
    ) {
        var tv_keyword = itemView.findViewById<TextView>(R.id.tv_alarmlist_keyword)
        var tv_price = itemView.findViewById<TextView>(R.id.tv_alarmlist_price)
    }

}
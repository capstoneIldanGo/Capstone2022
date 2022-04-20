package com.ildango.capstone.myalarmlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.ildango.capstone.R
import com.ildango.capstone.mywishlist.MyWishItem

class MyAlarmListAdapter(private var items: LiveData<ArrayList<MyAlarmItem>>)
    : RecyclerView.Adapter<MyAlarmListAdapter.MyAlarmListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAlarmListViewHolder {
        return MyAlarmListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MyAlarmListViewHolder, position: Int) {
        items.value!!.get(position).let { item ->
            with(holder) {
                tv_keyword.text = item.keyword
                tv_price.text = item.price
            }
        }
    }

    override fun getItemCount(): Int {
        return items.value!!.size
    }

    inner class MyAlarmListViewHolder constructor(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_alarm_listview, parent, false)
    ) {
        var tv_keyword = itemView.findViewById<TextView>(R.id.tv_alarmlist_keyword)
        var tv_price = itemView.findViewById<TextView>(R.id.tv_alarmlist_price)
    }

}
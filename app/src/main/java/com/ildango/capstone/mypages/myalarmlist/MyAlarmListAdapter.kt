package com.ildango.capstone.mypages.myalarmlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ildango.capstone.R
import com.ildango.capstone.data.model.MyAlarmItem
import retrofit2.Response

class MyAlarmListAdapter(private var items: MutableLiveData<Response<List<MyAlarmItem>>>)
    : RecyclerView.Adapter<MyAlarmListAdapter.MyAlarmListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAlarmListViewHolder {
        return MyAlarmListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MyAlarmListViewHolder, position: Int) {
        items.value!!.body()!!.get(position).let { item ->
            with(holder) {
                tv_keyword.text = item.itemName
                tv_price.text = "${item.targetPrice}원"
            }
        }
    }

    override fun getItemCount(): Int {
        return items.value!!.body()!!.size
    }

    inner class MyAlarmListViewHolder constructor(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_alarm_listview, parent, false)
    ) {
        var tv_keyword = itemView.findViewById<TextView>(R.id.tv_alarmlist_keyword)
        var tv_price = itemView.findViewById<TextView>(R.id.tv_alarmlist_price)
    }

}
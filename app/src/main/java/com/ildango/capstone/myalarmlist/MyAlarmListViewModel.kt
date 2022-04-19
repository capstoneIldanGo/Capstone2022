package com.ildango.capstone.myalarmlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MyAlarmListViewModel(application: Application) : AndroidViewModel(application) {
    var liveData : MutableLiveData<ArrayList<MyAlarmItem>> = MutableLiveData<ArrayList<MyAlarmItem>>()

    init {
        var AlarmItem = ArrayList<MyAlarmItem>()
        // dummy data
        AlarmItem.add(MyAlarmItem("test1", "100000원"))
        AlarmItem.add(MyAlarmItem("test2", "40000000원"))
        AlarmItem.add(MyAlarmItem("test3", "25000원"))

        liveData.postValue(AlarmItem)
    }

    // 찜 목록에서 아이템 선택해서 삭제
    fun deleteItems() {

    }
}
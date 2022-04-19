package com.ildango.capstone

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ildango.capstone.myalarmlist.MyAlarmItem
import com.ildango.capstone.mywishlist.MyWishItem

class MyViewModel(application: Application) : AndroidViewModel(application) {

    var wishLiveData : MutableLiveData<ArrayList<MyWishItem>> = MutableLiveData<ArrayList<MyWishItem>>()
    var alarmLiveData : MutableLiveData<ArrayList<MyAlarmItem>> = MutableLiveData<ArrayList<MyAlarmItem>>()

    fun addWishItems() {
        var WishItem = ArrayList<MyWishItem>()
        // dummy data
        WishItem.add(MyWishItem("imgUrl", "test1", "100000원"))
        WishItem.add(MyWishItem("imgUrl", "test2", "40000000원"))
        WishItem.add(MyWishItem("imgUrl", "test3", "25000원"))

        wishLiveData.postValue(WishItem)
    }

    fun addAlarmItems() {
        var AlarmItem = ArrayList<MyAlarmItem>()
        // dummy data
        AlarmItem.add(MyAlarmItem("test1", "15000원"))
        AlarmItem.add(MyAlarmItem("test1", "15000원"))
        AlarmItem.add(MyAlarmItem("test1", "15000원"))
        AlarmItem.add(MyAlarmItem("test1", "15000원"))

        alarmLiveData.postValue(AlarmItem)
    }

    // 찜 목록에서 아이템 선택해서 삭제
    fun deleteItems() {

    }

}
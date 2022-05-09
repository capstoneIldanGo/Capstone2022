package com.ildango.capstone.mypages

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ildango.capstone.mypages.myalarmlist.MyAlarmItem
import com.ildango.capstone.mypages.mywishlist.MyWishItem

class MyViewModel(application: Application) : AndroidViewModel(application) {

    var wishLiveData : MutableLiveData<ArrayList<MyWishItem>> = MutableLiveData<ArrayList<MyWishItem>>()
    var alarmLiveData : MutableLiveData<ArrayList<MyAlarmItem>> = MutableLiveData<ArrayList<MyAlarmItem>>()

    init {
       // addWishItems()
        addAlarmItems()
    }
/*
    fun addWishItems() {
        var wishItem = ArrayList<MyWishItem>()
        // dummy data
        wishItem.add(MyWishItem("imgUrl", "test1", "100000원"))
        wishItem.add(MyWishItem("imgUrl", "test2", "40000000원"))
        wishItem.add(MyWishItem("imgUrl", "test3", "25000원"))

        wishLiveData.postValue(wishItem)
    }*/

    fun addAlarmItems() {
        var alarmItem = ArrayList<MyAlarmItem>()
        // dummy data
        alarmItem.add(MyAlarmItem("test1", "15000원"))
        alarmItem.add(MyAlarmItem("test1", "15000원"))
        alarmItem.add(MyAlarmItem("test1", "15000원"))
        alarmItem.add(MyAlarmItem("test1", "15000원"))

        alarmLiveData.postValue(alarmItem)
    }

    // 찜 목록에서 아이템 선택해서 삭제
    fun deleteItems() {

    }

}
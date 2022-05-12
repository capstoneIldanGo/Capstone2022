package com.ildango.capstone.mypages.myalarmlist

class MyAlarmItemModel:ArrayList<MyAlarmItem>()

data class MyAlarmItem (
    val priceAlarmId:Long,
    val targetPrice:Int,

    val userId:Long,
    val userName:String,

    val itemId:Long,
    val itemName:String
)
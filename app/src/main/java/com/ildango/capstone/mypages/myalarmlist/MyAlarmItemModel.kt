package com.ildango.capstone.mypages.myalarmlist

class MyAlarmItemModel:ArrayList<MyAlarmItem>()

data class MyAlarmItem (
    val keyword : String,
    val price : String
)
package com.ildango.capstone.myalarmlist

class MyAlarmItemModel:ArrayList<MyAlarmItem>()

data class MyAlarmItem (
    var keyword : String,
    var price : String
)
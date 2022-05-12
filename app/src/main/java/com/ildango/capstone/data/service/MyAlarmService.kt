package com.ildango.capstone.data.service

import com.ildango.capstone.mypages.myalarmlist.MyAlarmItem
import retrofit2.Response
import retrofit2.http.GET

interface MyAlarmService {
    @GET("pricealarm/17")
    suspend fun getAllAlarmList(): Response<List<MyAlarmItem>>
}
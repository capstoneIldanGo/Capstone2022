package com.ildango.capstone.data.service

import com.ildango.capstone.mypages.myalarmlist.MyAlarmItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyAlarmService {
    @GET("pricealarm/21")
    suspend fun getAllAlarmList(): Response<List<MyAlarmItem>>
}
package com.ildango.capstone.data.service

import com.ildango.capstone.data.model.MyAlarmItem
import retrofit2.Response
import retrofit2.http.GET

interface MyAlarmService {
    @GET("pricealarm/21")
    suspend fun getAllAlarmList(): Response<List<MyAlarmItem>>
}
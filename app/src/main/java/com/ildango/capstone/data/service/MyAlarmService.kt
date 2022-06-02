package com.ildango.capstone.data.service

import com.ildango.capstone.data.model.MyAlarmItem
import com.ildango.capstone.data.model.MyAlarmPostItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyAlarmService {
    @GET("pricealarm/1")
    suspend fun getAllAlarmList(): Response<List<MyAlarmItem>>

    @POST("pricealarm/add")
    suspend fun addAlarmItem(
        @Body item: MyAlarmPostItem
    ) : Response<Void>
}
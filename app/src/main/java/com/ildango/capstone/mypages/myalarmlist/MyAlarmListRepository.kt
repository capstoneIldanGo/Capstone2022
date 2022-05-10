package com.ildango.capstone.mypages.myalarmlist

import com.ildango.capstone.service.RetrofitClient
import retrofit2.Response

class MyAlarmListRepository {
    suspend fun getAlarmItem(): Response<List<MyAlarmItem>> {
        return RetrofitClient.productApi.getAllAlarmList()
    }
}
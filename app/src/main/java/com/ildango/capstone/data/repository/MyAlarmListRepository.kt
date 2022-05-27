package com.ildango.capstone.data.repository

import com.ildango.capstone.data.model.MyAlarmItem
import com.ildango.capstone.data.service.RetrofitClient
import retrofit2.Response

class MyAlarmListRepository {
    suspend fun getAlarmItem(): Response<List<MyAlarmItem>> {
        return RetrofitClient.alarmApi.getAllAlarmList()
    }
}
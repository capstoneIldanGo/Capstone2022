package com.ildango.capstone.data.repository

import com.ildango.capstone.data.service.RetrofitClient
import com.ildango.capstone.result.ChartItem
import retrofit2.Response

class ChartRepository() {
    suspend fun getChartPrice(keyword:String) : Result<List<ChartItem>>{
        return try {
            val data = RetrofitClient.chartApi.getChartPrice(keyword)
            if(data.isSuccessful) {
                data.body()?.let {
                    Result.success(it)
                }?: Result.failure(Throwable(data.message()))
            } else {
                Result.failure(Throwable(data.message()))
            }
        } catch (e:Exception) {
            Result.failure(Throwable(e.message))
        }
    }
}

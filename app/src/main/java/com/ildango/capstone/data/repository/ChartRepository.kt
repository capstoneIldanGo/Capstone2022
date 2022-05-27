package com.ildango.capstone.data.repository

import com.ildango.capstone.data.service.RetrofitClient
import com.ildango.capstone.result.ChartItem
import retrofit2.Response

class ChartRepository() {
    suspend fun getChartPrice(keyword:String) : Response<List<ChartItem>>{
        return RetrofitClient.chartApi.getChartPrice(keyword)
    }
}
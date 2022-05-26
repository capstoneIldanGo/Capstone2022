package com.ildango.capstone.data.repository

import com.ildango.capstone.data.service.RetrofitClient
import com.ildango.capstone.result.ChartPrice
import com.ildango.capstone.result.ChartPriceList
import com.ildango.capstone.resultdetail.ProductItemList
import retrofit2.Response

class ChartRepository {
    suspend fun getChartPrice(keyword:String, date: String) : Result<ChartPriceList> {
        var data : Response<ChartPriceList>

        return try {
            data = RetrofitClient.chartApi.getChartPrice(keyword, date)

            if(data.isSuccessful) {
                data.body()?.let {
                    Result.success(it)
                }?: Result.failure(Throwable(data.message()))
            } else {
                Result.failure(Throwable(data.message()))
            }
        } catch (e: Exception) {
            Result.failure(Throwable(e.message))
        }
    }
}

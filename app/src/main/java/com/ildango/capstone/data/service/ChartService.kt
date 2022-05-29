package com.ildango.capstone.data.service

import com.ildango.capstone.result.ChartItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChartService {
    @GET("marketprice")
    suspend fun getChartPrice(
        @Query("keyword", encoded=true) keyword : String
    ) : Response<List<ChartItem>>
}
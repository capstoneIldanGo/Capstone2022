package com.ildango.capstone.data.service

import com.ildango.capstone.result.ChartPriceList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChartService {
    @GET("marketprice")
    suspend fun getChartPrice(
        @Query("keyword", encoded=true) keyword : String,
        @Query("date") date : String
    ) : Response<ChartPriceList>
}
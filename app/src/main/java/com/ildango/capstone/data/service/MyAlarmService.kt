package com.ildango.capstone.data.service

import com.ildango.capstone.data.model.MyAlarmItem
import com.ildango.capstone.data.model.MyAlarmPostItem
import retrofit2.Response
import retrofit2.http.*

interface MyAlarmService {
    @GET("pricealarm/1")
    suspend fun getAllAlarmList(): Response<List<MyAlarmItem>>

    @POST("pricealarm/add")
    suspend fun addAlarmItem(
        @Body item: MyAlarmPostItem
    ) : Response<Void>

    @DELETE("pricealarm/delete/{userId}/{itemName}")
    suspend fun deleteAlarmItem(
        @Path("userId") userId:Long,
        @Path("itemName") itemName:String
    ) : Response<Void>

    @GET("pricealarm/exist")
    suspend fun isItemExistInMyAlarms(
        @Query("userId") userId: Long,
        @Query("itemName") itemName: String
    ) : Response<Boolean>

    @PUT("pricealarm/update")
    suspend fun updatePrice(
        @Query("userId") userId: Long,
        @Query("itemName") itemName: String,
        @Query("targetPrice") targetPrice: Int
    ) : Response<Void>
}
package com.ildango.capstone.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationService {
    @GET("location/city")
    suspend fun getCities(): Response<List<String>>

    @GET("location/state/{state}")
    suspend fun getStates(
        @Path("state", encoded = true) state:String
    ): Response<List<String>>

}
package com.ildango.capstone.data.repository

import com.ildango.capstone.data.service.RetrofitClient
import java.lang.Exception

class LocationRepository {

    suspend fun getCities(): Result<List<String>> {
        return try {
            val data = RetrofitClient.locationApi.getCities()
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

    suspend fun getStates(state:String): Result<List<String>> {
        return try {
            val data = RetrofitClient.locationApi.getStates(state)
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
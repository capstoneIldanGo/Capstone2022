package com.ildango.capstone.data.repository

import com.ildango.capstone.data.model.MyAlarmItem
import com.ildango.capstone.data.model.MyAlarmPostItem
import com.ildango.capstone.data.service.RetrofitClient
import retrofit2.Response
import java.lang.Exception

class MyAlarmListRepository {

    suspend fun deleteAlarmItem(userId:Long, itemName:String): Result<Void> {
        return try {
            val data = RetrofitClient.alarmApi.deleteAlarmItem(userId, itemName)
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

    suspend fun getAlarmItem(): Result<List<MyAlarmItem>> {
        return try {
            val data = RetrofitClient.alarmApi.getAllAlarmList()
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

    suspend fun addAlarmItem(item: MyAlarmPostItem) : Result<Void> {
        return try {
            val data = RetrofitClient.alarmApi.addAlarmItem(item)
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

    suspend fun changeAlarmPrice(userId: Long, itemName: String, price: Int): Result<Void> {
        return try {
            val result = RetrofitClient.alarmApi.updatePrice(userId, itemName, price)
            if(result.isSuccessful) {
                result.body()?.let {
                    Result.success(it)
                }?: Result.failure(Throwable(result.message()))
            } else {
                Result.failure(Throwable(result.message()))
            }
        } catch (e:Exception) {
            Result.failure(Throwable(e.message))
        }
    }

    suspend fun isItemExistInMyAlarms(userId: Long, itemName: String): Result<Boolean> {
        return try {
            val result = RetrofitClient.alarmApi.isItemExistInMyAlarms(userId, itemName)
            if (result.isSuccessful) {
                result.body()?.let {
                    Result.success(it)
                }?: Result.failure(Throwable(result.message()))
            } else {
                Result.failure(Throwable(result.message()))
            }
        } catch (e:Exception) {
            Result.failure(Throwable(e.message))
        }
    }
}
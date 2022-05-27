package com.ildango.capstone.data.repository

import com.ildango.capstone.data.model.MyWishItem
import com.ildango.capstone.data.service.RetrofitClient
import com.ildango.capstone.data.model.MyWishPostItem
import retrofit2.Response
import java.lang.Exception

class MyWishListRepository {
    suspend fun getWishItem() : Result<List<MyWishItem>> {
        return try {
            val data = RetrofitClient.wishApi.getAllWishProduct()
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

    suspend fun addWishItem(item: MyWishPostItem) : Result<Int> {
        return try {
            val data = RetrofitClient.wishApi.setMyPost(item)
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

    suspend fun isItemExistInMyPosts(userId:Long, postId:Long): Result<Boolean> {
        return try {
            val result = RetrofitClient.wishApi.isItemExistInMyPosts(userId, postId)
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
}
package com.ildango.capstone.data.repository

import com.ildango.capstone.mypages.mywishlist.MyWishItem
import com.ildango.capstone.data.service.RetrofitClient
import com.ildango.capstone.mypages.mywishlist.MyWishPostItem
import retrofit2.Response
import java.lang.Exception

class MyWishListRepository {
    suspend fun getWishItem() : Response<List<MyWishItem>> {
        return RetrofitClient.wishApi.getAllWishProduct()
    }

    suspend fun setWishItem(item: MyWishPostItem) : Result<Int> {
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
}
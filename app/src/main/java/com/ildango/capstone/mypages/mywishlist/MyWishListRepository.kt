package com.ildango.capstone.mypages.mywishlist

import com.ildango.capstone.service.RetrofitClient
import retrofit2.Response

class MyWishListRepository {
    suspend fun getWishItem() : Response<List<MyWishItem>> {
        return RetrofitClient.productApi.getAllWishProduct()
    }
}
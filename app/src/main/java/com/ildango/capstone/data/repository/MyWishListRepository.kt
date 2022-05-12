package com.ildango.capstone.data.repository

import com.ildango.capstone.mypages.mywishlist.MyWishItem
import com.ildango.capstone.data.service.RetrofitClient
import retrofit2.Response

class MyWishListRepository {
    suspend fun getWishItem() : Response<List<MyWishItem>> {
        return RetrofitClient.wishApi.getAllWishProduct()
    }
}
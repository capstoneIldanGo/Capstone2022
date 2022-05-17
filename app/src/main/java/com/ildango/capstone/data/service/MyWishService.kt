package com.ildango.capstone.data.service

import com.ildango.capstone.mypages.mywishlist.MyWishItem
import com.ildango.capstone.mypages.mywishlist.MyWishPostItem
import retrofit2.Response
import retrofit2.http.*

interface MyWishService {
    @GET("myposts/21")      // 21 : userId
    suspend fun getAllWishProduct() : Response<List<MyWishItem>>

    @POST("myposts/add")
    suspend fun setMyPost(@Body item:MyWishPostItem): Response<Int>
}
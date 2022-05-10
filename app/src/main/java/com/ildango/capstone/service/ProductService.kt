package com.ildango.capstone.service

import com.ildango.capstone.mypages.myalarmlist.MyAlarmItem
import com.ildango.capstone.mypages.mywishlist.MyWishItem
import com.ildango.capstone.resultdetail.ProductItem
import com.ildango.capstone.resultdetail.ProductItemList
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("post")         // 40 : postId
    suspend fun getAllProduct() : Response<ProductItemList>

    @GET("myposts/17")      // 17 : userId
    suspend fun getAllWishProduct() : Response<List<MyWishItem>>

    @GET("pricealarm/17")
    suspend fun getAllAlarmList(): Response<List<MyAlarmItem>>
}
package com.ildango.capstone.data.service

import com.ildango.capstone.data.model.ProductItemList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("post")         // 40 : postId
    suspend fun getAllProduct(
        @Query("keyword", encoded = true) keyword:String,
        @Query("ordering") order:String,
        @Query("isSold") sold:Boolean ?= false,
        @Query("page") page:Int,
        @Query("isMint") mint:Boolean ?= null,
        @Query("platform") platform:String ?= null
    ) : Response<ProductItemList>
}
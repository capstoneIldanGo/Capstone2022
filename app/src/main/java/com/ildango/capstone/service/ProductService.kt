package com.ildango.capstone.service

import com.ildango.capstone.mypages.mywishlist.MyWishItem
import com.ildango.capstone.resultdetail.ProductItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("post/40")         // 40 : postId
    suspend fun getAllProduct() : Response<ProductItem>

    @GET("myposts/17")      // 17 : userId
    suspend fun getAllWishProduct() : Response<List<MyWishItem>>
}
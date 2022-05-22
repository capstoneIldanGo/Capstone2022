package com.ildango.capstone.data.service

import com.ildango.capstone.resultdetail.ProductItemList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("post")         // 40 : postId
    suspend fun getAllProduct(
        @Query("ordering") order:String,
        @Query("page") page:Int,
        @Query("sold") sold:Boolean
    ) : Response<ProductItemList>

 //   post?ordering=UPLOADDATE_DESC
 //   post?ordering=PRICE_ASC
}
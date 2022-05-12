package com.ildango.capstone.data.service

import com.ildango.capstone.resultdetail.ProductItemList
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("post")         // 40 : postId
    suspend fun getAllProduct() : Response<ProductItemList>

 //   post?ordering=UPLOADDATE_DESC
 //   post?ordering=PRICE_ASC
}
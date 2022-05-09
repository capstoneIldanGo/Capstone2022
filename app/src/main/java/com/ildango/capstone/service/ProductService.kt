package com.ildango.capstone.service

import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("post/40")
    suspend fun getAllProduct() : Response<ProductItem>
}
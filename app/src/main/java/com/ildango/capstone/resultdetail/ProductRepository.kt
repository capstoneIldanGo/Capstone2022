package com.ildango.capstone.resultdetail

import com.ildango.capstone.service.RetrofitClient
import retrofit2.Response

class ProductRepository {
    suspend fun getAllProduct() : Response<ProductItem> {
        return RetrofitClient.productApi.getAllProduct()
    }
}
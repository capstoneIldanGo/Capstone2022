package com.ildango.capstone.data.repository

import com.ildango.capstone.resultdetail.ProductItemList
import com.ildango.capstone.data.service.RetrofitClient
import retrofit2.Response

class ProductRepository {
    suspend fun getAllProduct() : Response<ProductItemList> {
        return RetrofitClient.productApi.getAllProduct()
    }
}
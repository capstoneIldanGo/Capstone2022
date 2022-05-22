package com.ildango.capstone.data.repository

import com.ildango.capstone.resultdetail.ProductItemList
import com.ildango.capstone.data.service.RetrofitClient
import retrofit2.Response
import java.lang.Exception

class ProductRepository {
    suspend fun getAllProduct(order:String, page:Int) : Result<ProductItemList> {
        return try {
            val data = RetrofitClient.productApi.getAllProduct(order, page, false)
            if(data.isSuccessful) {
                data.body()?.let {
                    Result.success(it)
                }?: Result.failure(Throwable(data.message()))
            } else {
                Result.failure(Throwable(data.message()))
            }
        } catch (e: Exception) {
            Result.failure(Throwable(e.message))
        }
    }
}
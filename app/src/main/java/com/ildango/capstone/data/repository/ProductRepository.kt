package com.ildango.capstone.data.repository

import android.util.Log
import com.ildango.capstone.resultdetail.ProductItemList
import com.ildango.capstone.data.service.RetrofitClient
import retrofit2.Response
import java.lang.Exception

class ProductRepository {
    suspend fun getAllProduct(
        keyword: String,
        order: String,
        page: Int,
        tag: List<Boolean>
    ): Result<ProductItemList> {

        val data: Response<ProductItemList>

        return try {
            val mint = if(tag[1]) true else null

            data = RetrofitClient.productApi.getAllProduct(keyword, order, page, mint = mint)

            if (data.isSuccessful) {
                data.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Throwable(data.message()))
            } else {
                Result.failure(Throwable(data.message()))
            }
        } catch (e: Exception) {
            Result.failure(Throwable(e.message))
        }
    }
}
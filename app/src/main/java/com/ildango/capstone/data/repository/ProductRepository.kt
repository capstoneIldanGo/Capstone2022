package com.ildango.capstone.data.repository

import android.util.Log
import com.ildango.capstone.data.model.ProductItem
import com.ildango.capstone.data.model.ProductItemList
import com.ildango.capstone.data.service.RetrofitClient
import com.ildango.capstone.resultdetail.orderByPrice
import retrofit2.Response
import java.lang.Exception

class ProductRepository {

    private val platforms = listOf("Joongonara", "Bunjang")

    suspend fun getAllProduct(
        keyword: String,
        order: String,
        page: Int,
        tag: List<Boolean>,
        platform: List<Boolean>
    ): Result<ProductItemList> {

        val data: Response<ProductItemList>

        return try {
            val mint = if(tag[1]) true else null
            val area = if(tag[0]) true else null
            val platform = getPlatform(platform)

            data = RetrofitClient.productApi.getAllProduct(
                keyword=keyword, order=order, page=page, mint = mint, platform = platform)

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

    suspend fun getProductPrice(
        keyword: String,
        mint: Boolean ?= null,
        area: Boolean ?= null
    ): Result<ProductItem> {
        return try {
            val data = RetrofitClient.productApi.getAllProduct(
                keyword = keyword,
                order = orderByPrice,
                page = 0,
                mint = mint
            )

            if (data.isSuccessful) {
                data.body()?.let {
                    Result.success(it.productList[0])
                } ?: Result.failure(Throwable(data.message()))
            } else {
                Result.failure(Throwable(data.message()))
            }
        } catch (e: Exception) {
            Result.failure(Throwable(e.message))
        }
    }

    private fun getPlatform(platform: List<Boolean>): String? {
        return if(platform[0]&&platform[1])
            null
        else if(platform[0])
            platforms[0]
        else if(platform[1])
            platforms[1]
        else
            null
    }
}
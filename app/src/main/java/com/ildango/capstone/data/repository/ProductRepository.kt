package com.ildango.capstone.data.repository

import com.ildango.capstone.resultdetail.ProductItemList
import com.ildango.capstone.data.service.RetrofitClient
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

            data = RetrofitClient.productApi.getAllProduct(keyword=keyword, order=order, page=page, mint = mint, platform = platform)

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
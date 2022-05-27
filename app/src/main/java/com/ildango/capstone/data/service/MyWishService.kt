package com.ildango.capstone.data.service

import com.ildango.capstone.data.model.MyWishItem
import com.ildango.capstone.data.model.MyWishPostItem
import retrofit2.Response
import retrofit2.http.*

interface MyWishService {
    @GET("myposts/1")      // 21 : userId
    suspend fun getAllWishProduct() : Response<List<MyWishItem>>

    @POST("myposts/add")
    suspend fun setMyPost(@Body item: MyWishPostItem): Response<Int>

    @GET("myposts/exist")   /// myposts/exist?userId=3&postId=4
    suspend fun isItemExistInMyPosts(
        @Query("userId") userId:Long,
        @Query("postId") postId:Long
    ): Response<Boolean>
}
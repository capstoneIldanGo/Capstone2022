package com.ildango.capstone.data.service

import com.ildango.capstone.data.model.MyWishItem
import com.ildango.capstone.data.model.MyWishPostItem
import retrofit2.Response
import retrofit2.http.*

interface MyWishService {
    @GET("myposts/1")      // 1 : userId
    suspend fun getAllWishProduct() : Response<List<MyWishItem>>

    @POST("myposts/add")
    suspend fun setMyPost(@Body item: MyWishPostItem): Response<Void>

    @GET("myposts/exist")
    suspend fun isItemExistInMyPosts(
        @Query("userId") userId:Long,
        @Query("postId") postId:Long
    ): Response<Boolean>

    @DELETE("myposts/delete/{userId}/{postId}")
    suspend fun deleteMyPostItem(
        @Path("userId") userId: Long,
        @Path("postId") postId: Long
    ): Response<Void>

}
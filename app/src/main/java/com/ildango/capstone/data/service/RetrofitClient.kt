package com.ildango.capstone.data.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://3.36.254.182/api/"

    private val interceptor = HttpLoggingInterceptor()
    private val client : OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val instance : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val productApi: ProductService by lazy {
        instance.create(ProductService::class.java)
    }
    val alarmApi: MyAlarmService by lazy {
        instance.create(MyAlarmService::class.java)
    }
    val wishApi: MyWishService by lazy {
        instance.create(MyWishService::class.java)
    }

}
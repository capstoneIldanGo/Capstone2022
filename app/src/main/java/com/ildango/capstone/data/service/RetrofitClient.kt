package com.ildango.capstone.data.service

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://3.36.254.182/api/"

    private fun getInterceptor() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    private fun getApiClient():Retrofit {
        val client : OkHttpClient = OkHttpClient.Builder().addInterceptor(getInterceptor()).build()
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val productApi: ProductService by lazy {
        getApiClient().create(ProductService::class.java)
    }
    val alarmApi: MyAlarmService by lazy {
        getApiClient().create(MyAlarmService::class.java)
    }
    val wishApi: MyWishService by lazy {
        getApiClient().create(MyWishService::class.java)
    }

}
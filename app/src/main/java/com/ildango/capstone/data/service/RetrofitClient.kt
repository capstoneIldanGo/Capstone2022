package com.ildango.capstone.data.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "http://3.36.254.182/api/"

    private fun getInterceptor() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    private fun getApiClient():Retrofit {
        val client : OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(getInterceptor()).build()
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val instance = getApiClient()

    val productApi: ProductService by lazy {
        instance.create(ProductService::class.java)
    }
    val alarmApi: MyAlarmService by lazy {
        instance.create(MyAlarmService::class.java)
    }
    val wishApi: MyWishService by lazy {
        instance.create(MyWishService::class.java)
    }
    val chartApi : ChartService by lazy {
        instance.create(ChartService::class.java)
    }
    val locationApi : LocationService by lazy {
        instance.create(LocationService::class.java)
    }

}
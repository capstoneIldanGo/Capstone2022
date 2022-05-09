package com.ildango.capstone.service

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://3.36.254.182/api/"
/*    private var instance:Retrofit ?= null
    private val gson = GsonBuilder().setLenient().create()


    fun getInstance():Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }

        val client : OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return instance!!
    }*/

    private val interceptor = HttpLoggingInterceptor()
    private val client : OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val instance : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val productApi:ProductService by lazy {
        instance.create(ProductService::class.java)
    }

}
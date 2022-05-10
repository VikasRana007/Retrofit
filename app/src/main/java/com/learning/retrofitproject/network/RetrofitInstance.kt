package com.learning.retrofitproject.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    // companion objects are initialized when the class is loaded for the very first ime
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/albums/"

        private val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(
                    30,
                    TimeUnit.SECONDS
                )  // This is time taken by app try to connect with server for data
                .readTimeout(
                    20,
                    TimeUnit.SECONDS
                )     // Max time gap between arrival of two data packet from server
                .writeTimeout(
                    25,
                    TimeUnit.SECONDS
                )    // Max time gap between two data packet when sending them to the server
        }.build()

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}
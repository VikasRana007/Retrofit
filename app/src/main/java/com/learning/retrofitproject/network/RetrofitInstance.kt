package com.learning.retrofitproject.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    // companion objects are initialized when the class is loaded for the very first ime
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/albums/"
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}
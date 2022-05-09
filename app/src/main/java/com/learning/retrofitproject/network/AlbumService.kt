package com.learning.retrofitproject.network

import com.learning.retrofitproject.data.Albums
import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {
    //"https://jsonplaceholder.typicode.com/albums/"
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>
}
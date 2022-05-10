package com.learning.retrofitproject.network

import com.learning.retrofitproject.data.Albums
import com.learning.retrofitproject.data.AlbumsItem
import retrofit2.Response
import retrofit2.http.*

interface AlbumService {
    //"https://jsonplaceholder.typicode.com/albums/"
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums")
    suspend fun getSortedAlbum(@Query("userID") userId:Int): Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id")albumId:Int) : Response<AlbumsItem>

     @POST("/albums")
    suspend fun uploadAlbum(@Body albumsItem: AlbumsItem) : Response<AlbumsItem>

}


package com.learning.retrofitproject

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.learning.retrofitproject.data.Albums
import com.learning.retrofitproject.data.AlbumsItem
import com.learning.retrofitproject.network.AlbumService
import com.learning.retrofitproject.network.RetrofitInstance
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService : AlbumService
    private lateinit var text_data:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text_data  = findViewById(R.id.remote_data)
        retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)

//        getRequestWithQueryParameter()
//        getRequestWithPathParameters()
        uploadAlbum()

    }

    private fun getRequestWithQueryParameter(){
        // Now Using a co routine Live Data builder we will get the Retrofit response as Live Data
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response: Response<Albums> = retService.getSortedAlbum(4)
            emit(response)
        }
        // Observe this live data response
        responseLiveData.observe(this, Observer {
            // this list iterator return a list iteratorList over the elements in this list in a proper sequence.
            val albumlist: MutableListIterator<AlbumsItem>? = it.body()?.listIterator()

            if (albumlist != null) {
                while (albumlist.hasNext()) {
                    val albumItem = albumlist.next()
                    val result: String = " " + "Album Title : ${albumItem.title}" + "\n" +
                            " " + "Album Id : ${albumItem.id}" + "\n" +
                            " " + "User Id : ${albumItem.userId}" + "\n\n\n"
                    text_data.append(result)

                }
            }
        })
    }

    private fun getRequestWithPathParameters(){
        // path parameter exam
        val pathResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response: Response<AlbumsItem> = retService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this, Observer {
            val title: String? = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
        })
    }


    private fun uploadAlbum(){
        val album = AlbumsItem(0,"Vikas Rana", 3)
        val postResponses : LiveData<Response<AlbumsItem>> = liveData {
            val postResponse : Response<AlbumsItem> = retService.uploadAlbum(album)
            emit(postResponse)
        }
        // now using observable
        postResponses.observe(this, Observer {
            val receivedAlbumItem = it.body()
            val result: String = " " + "Album Title : ${receivedAlbumItem?.title}" + "\n" +
                    " " + "Album Id : ${receivedAlbumItem?.id}" + "\n" +
                    " " + "User Id : ${receivedAlbumItem?.userId}" + "\n\n\n"

            text_data.text = result

        })


    }


//    {
//        "id": 101,
//        "title": "Vikas Rana",
//        "userId": 3
//    }







}
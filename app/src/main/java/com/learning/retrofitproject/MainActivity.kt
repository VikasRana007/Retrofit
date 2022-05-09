package com.learning.retrofitproject

import android.os.Bundle
import android.util.Log
import android.widget.TextView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text_data: TextView = findViewById(R.id.remote_data)
        val retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)
        // Now Using a co routine Live Data builder we will get the Retrofit response as Live Data
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response: Response<Albums> = retService.getAlbums()
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

}
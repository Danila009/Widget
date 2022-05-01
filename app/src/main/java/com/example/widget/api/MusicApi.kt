package com.example.widget.api

import com.example.widget.api.model.Music
import retrofit2.Response
import retrofit2.http.GET

interface MusicApi {

    @GET("/spotify/Music/7")
    suspend fun getMusic():Response<Music>
}
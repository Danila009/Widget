package com.example.widget.api.repository

import com.example.widget.api.MusicApi
import com.example.widget.api.model.Music
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val musicApi: MusicApi
):MusicApi {
    override suspend fun getMusic(): Response<List<Music>> {
        return musicApi.getMusic()
    }
}
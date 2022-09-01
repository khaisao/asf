package com.example.homewordretrofit.network

import com.example.homewordretrofit.model.Music
import com.example.homewordretrofit.model.MusicX
import retrofit2.http.GET

interface MusicService {
    @GET("automotive-media/music.json")
    suspend fun getAllMusicwithCoroutines(): Music
}
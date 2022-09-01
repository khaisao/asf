package com.example.homewordretrofit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MusicClient {
    private const val BASE_URL = "https://storage.googleapis.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    operator fun invoke() : MusicService = retrofit.create(MusicService::class.java)
}
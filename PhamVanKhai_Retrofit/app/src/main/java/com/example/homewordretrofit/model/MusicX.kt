package com.example.homewordretrofit.model

import java.io.Serializable

data class MusicX(
    var album: String = "",
    var artist: String = "",
    var duration: Int = 0,
    var genre: String = "",
    var image: String = "",
    var site: String = "",
    var source: String = "",
    var title: String="",
    var totalTrackCount: Int = 0,
    var trackNumber: Int = 0
):Serializable
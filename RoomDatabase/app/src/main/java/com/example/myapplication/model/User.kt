package com.example.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0,
    @ColumnInfo
    var account: String = "",
    @ColumnInfo
    var password: String = ""
)

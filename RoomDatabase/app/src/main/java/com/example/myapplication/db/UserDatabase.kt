package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.model.User

@Database(entities = [User::class], exportSchema = false, version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase = instance ?: synchronized(this) {
            Room.databaseBuilder(context, UserDatabase::class.java, "user.db").build()
        }
    }
}
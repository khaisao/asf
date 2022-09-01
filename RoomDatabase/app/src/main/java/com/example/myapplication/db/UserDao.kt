package com.example.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("Select * From User")
    fun getallUserwithLiveData(): LiveData<List<User>>

    @Query("DELETE FROM User WHERE _id = :id ")
    suspend fun deleteUserwithId(id: Int)
    @Update
    suspend fun updateUser(user: User)
}
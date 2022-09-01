package com.example.myapplication.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.db.UserDatabase
import com.example.myapplication.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val application: Application) : ViewModel() {
    private val userDao = UserDatabase.getInstance(application).getUserDao()
    val users = userDao.getallUserwithLiveData()
    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertUser(user)

        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.deleteUserwithId(id)

        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.updateUser(user)

        }
    }


}
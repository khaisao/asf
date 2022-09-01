package com.example.testbase.ui

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application
) : BindingViewModel() {
    private val _postFromPostId = MutableLiveData<Int>()
    val postFromPostId: LiveData<Int>
        get() = _postFromPostId

    fun abc() {
        _postFromPostId.value?.plus(1)
    }

    fun setvalue() {
        _postFromPostId.value = 0
    }

    val ddd = flow {
        var abc = 10
        while (abc<100){
            abc++
            delay(1000)
            emit(abc)
        }
    }

    @get:Bindable
    var loading: String by bindingProperty("laareh")
        private set

    fun changeLoading(){
        loading = UUID.randomUUID().toString()
    }

}


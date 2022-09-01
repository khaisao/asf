package com.example.testbase.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingActivity<T: ViewDataBinding> constructor(
    @LayoutRes private val contentLayoutId: Int
): AppCompatActivity() {

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, contentLayoutId)
        binding.lifecycleOwner = this
        setUpViews()
        addData()
        addEvent()
        addObservers()
    }

    open fun addObservers() {}

    open fun addEvent() {}

    open fun addData() {}

    open fun setUpViews() {}


}
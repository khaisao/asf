package com.example.testbase.util

import com.example.testbase.util.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Ali Asadi on 13/05/2020
 **/
object DispatchersProviderImpl : DispatchersProvider {
    override fun getMain(): CoroutineDispatcher = Dispatchers.Main
    override fun getMainImmediate(): CoroutineDispatcher = Dispatchers.Main.immediate
    override fun getIO(): CoroutineDispatcher = Dispatchers.IO
    override fun getDefault(): CoroutineDispatcher = Dispatchers.Default
}
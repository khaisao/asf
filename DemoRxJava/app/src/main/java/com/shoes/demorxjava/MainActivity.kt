package com.shoes.demorxjava

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.viewbinding.ViewBinding
import com.shoes.demorxjava.base.ViewBindingActivity
import com.shoes.demorxjava.databinding.ActivityMainBinding
import com.shoes.demorxjava.model.User
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var notificationChannel: NotificationChannel

    @Inject
    lateinit var notificationManager: NotificationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val observable = Observable.interval(2, TimeUnit.SECONDS).takeWhile { it<5 }
        observable.subscribe {
            Log.d("liajsglkjasg", "onCreate: ${it.toString()}")
        }


    }
}
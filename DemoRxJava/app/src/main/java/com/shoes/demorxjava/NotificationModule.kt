package com.shoes.demorxjava

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {

    @Singleton
    @Provides
    fun providerNotificationManager(app: Application): NotificationManager {
        return app.applicationContext.getSystemService(NotificationManager::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun providerNotificationChannel(notificationManager: NotificationManager):NotificationChannel{
        val notificationChannel = NotificationChannel("id", "name", NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.setSound(null,null)
        notificationManager.createNotificationChannel(notificationChannel)
        return notificationChannel
    }
}
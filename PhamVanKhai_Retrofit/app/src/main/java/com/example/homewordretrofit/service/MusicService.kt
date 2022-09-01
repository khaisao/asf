package com.example.homewordretrofit.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.homewordretrofit.R
import com.example.homewordretrofit.broadcast.MusicBroadcast
import com.example.homewordretrofit.model.MusicX

class MusicService : Service() {

    val ACTION_PAUSE = 1
    val ACTION_RESUME = 2
    val ACTION_CLEAR = 3
    val ACTION_START = 4


    private var isPlaying: Boolean = false
    private var mediaPlayer = MediaPlayer()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val data = intent?.getSerializableExtra("music")
        if(data != null){
            startMusic(data as MusicX)
            sendNotification(data as MusicX)
        }


        val action = intent?.getIntExtra("action_broadcast", 0)
        if (action != null) {
            handleActionMusic(action)
        }
        Log.d("brodata", action.toString())
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun startMusic(music: MusicX) {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.setDataSource(music.source)
            mediaPlayer.prepare()
            mediaPlayer.start()
            Toast.makeText(this, "Playing ${music.title}", Toast.LENGTH_SHORT).show();
        } else {
            mediaPlayer.setDataSource(music.source)
            mediaPlayer.prepare()
            mediaPlayer.start()
            Toast.makeText(this, "Playing ${music.title}", Toast.LENGTH_SHORT).show();
        }
        isPlaying = true
        sendActionToActivity(ACTION_START)
    }



    private fun handleActionMusic(action: Int) {
        if (action == 1) {
            pauseMusic()
        }
        if (action == 2) {
            resumeMusic()
        }
        if (action == 3) {
            clearMusic()
            sendActionToActivity(ACTION_CLEAR)
        }
    }

    private fun clearMusic() {
        stopSelf()
    }

    private fun resumeMusic() {
        if (!isPlaying) {
            mediaPlayer.start()
            isPlaying = true
        }
        sendActionToActivity(ACTION_RESUME)
    }

    private fun pauseMusic() {
        if (isPlaying) {
            mediaPlayer.pause()
            isPlaying = false
        }
        sendActionToActivity(ACTION_PAUSE)
    }

    private fun sendNotification(data: MusicX) {
        val notificationLayout = RemoteViews(packageName, R.layout.notification)
        notificationLayout.setTextViewText(R.id.tv_name_song, data.title)
        val builder = NotificationCompat.Builder(this)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .setSound(null)
            .setSmallIcon(R.drawable.ic_launcher_foreground)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "1",
                "music",
                NotificationManager.IMPORTANCE_LOW
            )
            channel.setSound(null, null)
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId(channel.id)
        }
        val notification = builder.build()
        notificationLayout.setOnClickPendingIntent(
            R.id.img_stop_song,
            getPendingIntent(this, ACTION_PAUSE)
        )
        notificationLayout.setOnClickPendingIntent(
            R.id.img_play_song,
            getPendingIntent(this, ACTION_RESUME)
        )
        notificationLayout.setOnClickPendingIntent(
            R.id.img_stop_service,
            getPendingIntent(this, ACTION_CLEAR)
        )
        startForeground(1, notification);
    }

    private fun getPendingIntent(context: Context, action: Int): PendingIntent? {
        val intent = Intent(this, MusicBroadcast::class.java)
        intent.putExtra("action_music", action)
        return PendingIntent.getBroadcast(
            context.applicationContext,
            action,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun sendActionToActivity(action: Int) {
        val intent = Intent("action_to_act")
        intent.putExtra("action",action)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

}
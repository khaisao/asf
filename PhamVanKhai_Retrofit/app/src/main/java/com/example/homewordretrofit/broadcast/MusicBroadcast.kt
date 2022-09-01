package com.example.homewordretrofit.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.homewordretrofit.service.MusicService

class MusicBroadcast:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.getIntExtra("action_music",0)
        val intentService = Intent(context,MusicService::class.java)
        intentService.putExtra("action_broadcast",action)
        context?.startService(intentService)
    }
}
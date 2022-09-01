package com.example.homewordretrofit


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homewordretrofit.adapter.MusicAdapter
import com.example.homewordretrofit.callback.OnMusicItemClick
import com.example.homewordretrofit.databinding.ActivityMainBinding
import com.example.homewordretrofit.model.MusicX
import com.example.homewordretrofit.network.MusicClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.homewordretrofit.service.MusicService
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay


class MainActivity : AppCompatActivity(), OnMusicItemClick {
    private lateinit var binding: ActivityMainBinding
    private var isPlaying: Boolean = false
    val ACTION_PAUSE = 1
    val ACTION_RESUME = 2
    val ACTION_CLEAR = 3
    val ACTION_START = 4

    inner class broadCast : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.getIntExtra("action",0)
            val duration = intent?.getIntExtra("duration",0)
            handleLayoutMusic(action)
            Log.d("action_music",action.toString())
            Log.d("duration_music",duration.toString())
        }
    }

    private fun handleLayoutMusic(action: Int?) {
        if(action == ACTION_START){
            binding.lyMusic.visibility = View.VISIBLE
        }
        if(action == ACTION_PAUSE){

        }
        if(action == ACTION_RESUME){

        }
        if(action == ACTION_CLEAR){
            binding.lyMusic.visibility = View.GONE
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadCast(), IntentFilter("action_to_act"))
        lifecycleScope.launch(Dispatchers.IO) {
            val musics = MusicClient.invoke().getAllMusicwithCoroutines()
            val listMusic = musics.music
            lifecycleScope.launch(Dispatchers.Main) {
                for ((index, value) in listMusic.withIndex()) {
                    listMusic[index].image =
                        "https://storage.googleapis.com/automotive-media/" + value.image
                    listMusic[index].source =
                        "https://storage.googleapis.com/automotive-media/" + value.source
                }
                val adapter = MusicAdapter(listMusic, this@MainActivity)
                binding.rvMusic.adapter = adapter
                binding.rvMusic.layoutManager =
                    LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            }
        }
        binding.ivPlaySong.setOnClickListener {
            if(!isPlaying){
                sendActionToService(ACTION_RESUME)
            }
            if(job==null){
                job = lifecycleScope.launch {
                    val seekMax = binding.seekBar.max
                    val current = binding.seekBar.progress
                    for(i in current.. seekMax){
                        binding.seekBar.progress = i
                        delay(1000L)
                        binding.tvDurationCurrent.text = i.toString()
                    }
                }
            }
        }
        binding.ivStopSong.setOnClickListener {
            if(!isPlaying){
                sendActionToService(ACTION_PAUSE)
            }
            job?.cancel()
            job = null
        }
        binding.ivClearSong.setOnClickListener {
            sendActionToService(ACTION_CLEAR)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadCast())
    }

    override fun onClick(musicX: MusicX) {
        val intent = Intent(this, MusicService::class.java)
        intent.putExtra("music", musicX)
        binding.seekBar.max = musicX.duration
        binding.seekBar.progress = 0
        binding.tvDurationMax.text = musicX.duration.toString()
        updateDuration(musicX.duration)
        startService(intent)
    }

    var job: Job? = null
    private fun updateDuration(duration:Int) {
        job?.cancel()
        job = lifecycleScope.launch {
            for(i in 1..duration){
                delay(1000L)
                binding.seekBar.progress = i
                binding.tvDurationCurrent.text = i.toString()
            }
        }
    }

    private fun sendActionToService(action:Int){
        val intent = Intent(this, MusicService::class.java)
        intent.putExtra("action_broadcast",action)
        startService(intent)
    }
}
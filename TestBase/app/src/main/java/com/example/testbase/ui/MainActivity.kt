package com.example.testbase.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.testbase.BuildConfig
import com.example.testbase.R
import com.example.testbase.base.BaseBindingActivity
import com.example.testbase.databinding.ActivityMainBinding
import com.example.testbase.ui.dialog.CustomDialog
import com.example.testbase.util.RemoteUtil
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.collections.HashMap

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var firebaseRemoteConfig:FirebaseRemoteConfig
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()

    }

    private fun setUpView() {
        Timber.d("lkjaweg")
        with(binding) {
            vm = viewModel
            tvOk.setOnClickListener {
                viewModel.changeLoading()
            }
            val map = HashMap<String, Any>()
            map[RemoteUtil.VERSION] = BuildConfig.VERSION_CODE
            firebaseRemoteConfig=FirebaseRemoteConfig.getInstance()
            val configSettings = FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(1).build()
            firebaseRemoteConfig.setConfigSettingsAsync(configSettings)
            firebaseRemoteConfig.setDefaultsAsync(map)
            firebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener {
//                    dialogShow()
                    Log.d("ljawegjlkawegklj", "setUpView: ${firebaseRemoteConfig.getString(RemoteUtil.TITLE)}")
                    Log.d("ljawegjlkawegklj", "setUpView: ${firebaseRemoteConfig.getString(RemoteUtil.ISFORCE)}")
                    Log.d("ljawegjlkawegklj", "setUpView: ${firebaseRemoteConfig.getString(RemoteUtil.VERSION)}")
                    Log.d("ljawegjlkawegklj", "setUpView: ${firebaseRemoteConfig.getString(RemoteUtil.WHATNEW)}")
                }

        }
    }

    private fun dialogShow() {
        if(firebaseRemoteConfig.getLong(RemoteUtil.VERSION) <= BuildConfig.VERSION_CODE) return
        val dialog = CustomDialog(this)
        dialog.config=firebaseRemoteConfig
        dialog.show()
    }

}



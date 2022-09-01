package com.example.testbase.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.testbase.R
import com.example.testbase.util.RemoteUtil
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class CustomDialog(context: Context) : Dialog(context) {
    lateinit var config: FirebaseRemoteConfig
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tvTitle = findViewById<TextView>(R.id.tv_info)
        val tvContent = findViewById<TextView>(R.id.tv_content)
        val btnSkip = findViewById<Button>(R.id.btn_skip)
        val btnUpdate = findViewById<Button>(R.id.btn_update)
        if (this::config.isInitialized) {
//            tvTitle.text = config.getString(RemoteUtil.TITLE)
            tvContent.text = config.getString(RemoteUtil.WHATNEW)
            if (config.getBoolean(RemoteUtil.ISFORCE)) {
                btnSkip.visibility = View.GONE
                setCancelable(false)
            } else {
                btnUpdate.visibility = View.VISIBLE
            }
            btnSkip.setOnClickListener {
                dismiss()
            }


        }
    }

}
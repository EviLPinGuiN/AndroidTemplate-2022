package com.itis.template

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TestReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        TODO("Not yet implemented")

        intent?.extras?.getInt("")
    }
}
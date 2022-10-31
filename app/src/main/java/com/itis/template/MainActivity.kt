package com.itis.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itis.template.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private var notificationProvider: NotificationProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // from binding
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.run {

        }

        notificationProvider = NotificationProvider(this)
        notificationProvider?.showNotification(
            title = "Hellow",
            text = "Мяяяуууу"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationProvider = null
    }
}
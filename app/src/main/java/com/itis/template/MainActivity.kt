package com.itis.template

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.os.Process
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.itis.template.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private var binder: IHellAidlInterface? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = IHellAidlInterface.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // from binding
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.run {
            tvHelloMessage.setOnClickListener {
//                startService(Intent(this@MainActivity, HelloService::class.java).apply {
//                    putExtra("MEDIA_ACTION", MediaActions.STOP as Parcelable)
//                })
                binder?.setMusic(Song(id = 0, name = "Test", raw = R.raw.test))
            }
            tvHelloMessage1.setOnClickListener {
                binder?.pauseMusic()
            }
            tvHelloMessage2.setOnClickListener {
//                ContextCompat.startForegroundService(this@MainActivity, Intent(this@MainActivity, HelloService::class.java).apply {
//                    putExtra("MEDIA_ACTION", MediaActions.PLAY as Parcelable)
//                })
//                binder?.sum(2, 7)
                binder?.playMusic()
            }
        }
        val pid = Process.myPid()
        Log.e("MAIN", "process: $pid")
    }

    override fun onStart() {
        super.onStart()
        bindService(
            Intent(this@MainActivity, HelloService::class.java).apply {
                putExtra("MEDIA_ACTION", MediaActions.PLAY as Parcelable)
            },
            connection,
            Service.BIND_AUTO_CREATE
        )
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
}
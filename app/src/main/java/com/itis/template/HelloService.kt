package com.itis.template

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.util.Log

class HelloService : Service() {

    private var mediaPlayer = MediaPlayer()

    private val aidlBinder = object : IHellAidlInterface.Stub() {

        override fun sum(a: Int, b: Int) {
            try {
                sumInts(a, b)
            } catch (e: RemoteException) {
                Log.e("HelloService", e.message.orEmpty())
            }
        }

        override fun sumAsync(a: Int, b: Int, callback: AsyncCallback?) {
            callback?.onSuccess(Song(0, "", 0))
        }

        override fun playMusic() {
            Log.e("HelloService.aidlBinder", "playLocaleMusic")
            playLocaleMusic()
        }

        override fun pauseMusic() {
            pause()
        }

        override fun setMusic(song: Song?) {
            val pid = Process.myPid()
            song?.also {
                Log.e("HelloService.setMusic", "Name: ${song.name} process: $pid")
                mediaPlayer = MediaPlayer.create(applicationContext, it.raw)
            }
        }

        override fun setMusicFromBundle(bundle: Bundle?) {
            bundle?.getBundle("")
        }
    }

    inner class HelloBinder : Binder() {

        fun sum(a: Int, b: Int) {
            sumInts(a, b)
        }

        fun playMusic() {
            playLocaleMusic()
        }

        fun pauseMusic() {
            pause()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getParcelableExtra<MediaActions>("MEDIA_ACTION")?.let {
            when (it) {
                MediaActions.PLAY -> play()
                MediaActions.PAUSE -> pause()
                MediaActions.STOP -> stop()
                MediaActions.PREV -> TODO()
                MediaActions.NEXT -> TODO()
            }
        }
//        startForeground(
//            12,
//            NotificationCompat.Builder(this, "test")
//                .setContentTitle("Title")
//                .setContentText("Title")
//                .setPriority(Notification.PRIORITY_LOW)
//                .setSmallIcon(R.drawable.cote)
//                .build()
//        )
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = aidlBinder

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun sumInts(a: Int, b: Int): Int {
        val sum = a + b
        Log.e("HelloService", "Sum: $sum")
        return sum
    }

    private fun play() {
        Log.e("HelloService", "play")
    }

    private fun pause() {
        mediaPlayer.pause()
    }

    private fun stop() {
        Log.e("HelloService", "stop")
        mediaPlayer.stop()
    }

    private fun playLocaleMusic() {
        Log.e("HelloService", "playLocaleMusic")
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        mediaPlayer.run {
            start()
            setOnCompletionListener {
                stop() // or call next() for change track
            }
        }
    }

    private fun playRemoteMusic() {
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        mediaPlayer.run {
//            setDataSource("https://muzati.net/download?h=eyJpbWciOiIvX2xkLzE5OS84MzkwNjM1Ni5qcGciLCJ0cmFjayI6Ii9tdXNpYy8wLTAtMS0xOTk5NC0yMCIsInRpdGxlIjoi0J/QvtC70Y/RgNC90LDRjyDQl9Cy0LXQt9C00LAgKGZlYXQuIEJhdHJhaSkiLCJhcnRpc3QiOiJNb3NvdmljaCJ9#")
            prepareAsync()
            setOnPreparedListener {
                start()
            }
            setOnCompletionListener {
                stop() // or call next() for change track
            }
        }
    }
}
package com.itis.template

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toBitmapOrNull

class NotificationProvider(private val context: Context) {

    @SuppressLint("UseCompatLoadingForDrawables")
    fun showNotification(title: String, text: String) {
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val vibrations = arrayOf(100L, 200L).toLongArray()
        val audioAttributes: AudioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
        val sound: Uri = Uri.parse(
            "android.resource://" + context.packageName + "/" + R.raw.bike
        )

        val intent = Intent(context, MainActivity::class.java)
        val pending = PendingIntent.getActivity(
            context,
            100,
            intent,
            PendingIntent.FLAG_ONE_SHOT,
        )

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(
                context,
                context.getString(R.string.default_notification_channel_id)
            )
                .setSmallIcon(R.drawable.ic_baseline_sports_kabaddi_24)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setContentIntent(pending)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)

        context.getDrawable(R.drawable.cote)?.toBitmapOrNull()?.also {
            builder.setLargeIcon(it)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                context.getString(R.string.default_notification_channel_id),
                context.getString(R.string.default_notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lightColor = Color.BLUE
                vibrationPattern = vibrations
                setSound(sound, audioAttributes)
                setShowBadge(true)
            }.also {
                notificationManager.createNotificationChannel(it)
            }
        } else {
            builder
                .setVibrate(vibrations)
                .setSound(sound)
                .setLights(Color.BLUE, 100, 500)
        }

        notificationManager.notify(2132, builder.build())

    }
}
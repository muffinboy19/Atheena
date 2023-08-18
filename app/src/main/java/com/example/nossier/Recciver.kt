package com.example.nossier


import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Recciver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val channelId = "reminder_channel"
        val notificationId = 1 // You can use a unique ID for each notification

        val notification = context?.let {
            NotificationCompat.Builder(it, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Reminder")
                .setContentText("Time for your reminder!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()
        }


        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        if (notification != null) {
            context.let { NotificationManagerCompat.from(it) }
                .notify(notificationId, notification)
        }
    }
}

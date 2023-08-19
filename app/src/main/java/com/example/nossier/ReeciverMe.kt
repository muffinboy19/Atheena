package com.example.nossier


import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ReeciverMe : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val i = Intent(context,Homescreen::class.java)
        val pendingIntent = PendingIntent.getActivity(context,0,i,0)
        val builder = NotificationCompat.Builder(context!!,"garuav")
            .setContentTitle("Gaurav")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("This is your rmeinder for a journal ")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)




        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123,builder.build())

    }
}

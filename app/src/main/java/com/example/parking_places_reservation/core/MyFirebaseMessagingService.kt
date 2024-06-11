package com.example.parking_places_reservation.core

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.util.Log
import androidx.core.app.NotificationCompat

class MyFirebaseMessagingService : FirebaseMessagingService() {



    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        try {
            remoteMessage.notification?.let {
                sendNotification(it.title, it.body)
            }
        } catch (e: Exception) {
            Log.e("FCM", "Error handling the message: ${e.message}", e)
        }
    }

    override fun onNewToken(token: String) {
        Log.d("FCM", "Refreshed token: $token")
        // Send the token to the server
    }


    private fun sendNotification(title: String?, messageBody: String?) {
        val channelId = "default_channel_id"
        val channelName = "Default Channel"

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Since Android Oreo, notification channels are required.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, builder.build())
    }
}

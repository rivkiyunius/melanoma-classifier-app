package com.example.melanomaclassifier.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.melanomaclassifier.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("FCM_MESSAGE1", remoteMessage.toString())
        Log.d("FCM_MESSAGE2", remoteMessage.data.toString())

        remoteMessage.notification.let {
            if (remoteMessage.data.isNotEmpty()){
                val title = it?.title ?: "Notifikasi"
                val message = remoteMessage.data["message"].toString()
                showNotification(title, message)
            }
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("NEW_TOKEN", token)
    }

    private fun showNotification(title: String?, message: String){
        try {
            val vibrateTime = longArrayOf(0, 1000, 1000, 1000, 1000)

            val notificationIcon =
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) R.drawable.ic_home else R.mipmap.ic_launcher

            val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(notificationIcon)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setVibrate(vibrateTime)
                .setPriority(Notification.PRIORITY_HIGH)
                .setLights(Color.RED, 1, 0)
                .setColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                .setStyle(NotificationCompat.BigTextStyle().bigText(message))

            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
                notificationBuilder.setLargeIcon(
                    BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
                )
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channelId = "melanomaapp"
                val channelName = "Melanoma App"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val notificationChannel = NotificationChannel(channelId, channelName, importance)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.RED
                notificationChannel.enableVibration(true)
                notificationChannel.vibrationPattern = vibrateTime
                notificationBuilder.setChannelId(channelId)
                notificationManager.createNotificationChannel(notificationChannel)
            }

            notificationManager.cancel(1)
            notificationManager.notify(1, notificationBuilder.build())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}
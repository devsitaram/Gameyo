package com.sitaram.gameyo.features

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sitaram.gameyo.R
import java.util.Objects

class FirebaseMsgServices: FirebaseMessagingService() {

    // get token
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("Refresh Token: ", token)
    }

    // get message from firebase
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.notification == null) {
            pushNotification(message.notification?.title, message.notification?.body
            )
        }
    }

    private fun pushNotification(title: String?, message: String?) {
        val notificationBuilder = NotificationCompat.Builder(this, "gameyoChanel")
            .setSmallIcon(R.drawable.ic_email)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
        val manager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        manager.notify(101, notificationBuilder.build())
    }
//
//    fun notification() {
//        FirebaseMessaging.getInstance().token.addOnCompleteListener { task: Task<String?> ->
//                if (!task.isSuccessful) {
//                    Objects.requireNonNull(task.exception)?.localizedMessage?.let {
//                        Log.e("Error Message", it)
//                    }
//                }
//                // get new FCM registration token
//                val token = task.result
//
//                //  String message = getString(R.string.notification, token);
//                Log.d("Token", token!!)
//            }
//    }
}
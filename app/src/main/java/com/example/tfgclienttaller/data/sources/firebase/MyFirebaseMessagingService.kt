package com.example.tfgclienttaller.data.sources.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.tfgclienttaller.data.repositories.LocalRepository
import com.example.tfgclienttaller.domain.MyToken
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessagingService @Inject constructor() : FirebaseMessagingService() {

    @Inject
    lateinit var localRepository: LocalRepository

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(
            ConstantesFirebase.TAGMESSAGERECEIVE,
            ConstantesFirebase.FROM + "${remoteMessage.from}"
        )

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(
                ConstantesFirebase.TAGMESSAGERECEIVE,
                ConstantesFirebase.NOTIFICACION + "${remoteMessage.data}"
            )

            val title = remoteMessage.data[ConstantesFirebase.TITULO]
            val body = remoteMessage.data[ConstantesFirebase.BODY]
            notificacionRecibida(title, body)

        }
    }


    override fun onNewToken(token: String) {

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(
                    ConstantesFirebase.TOKENFIREBASE,
                    ConstantesFirebase.TOKENREGISTERFAIL,
                    task.exception
                )
                return@OnCompleteListener
            } else {
                val token = task.result
                localRepository.insertToken(MyToken(token))


            }

        })
    }

    private fun notificacionRecibida(title: String?, body: String?) {
        // construir canal
        val chanelID = ConstantesFirebase.CHANGELANDNOTINAME
        val chanelName = ConstantesFirebase.CHANGELANDNOTINAME

        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(chanelID, chanelName, importance)
        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        var notify = NotificationCompat.Builder(this, chanelID)
            .setSmallIcon(com.google.firebase.messaging.ktx.R.drawable.gcm_icon)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val notiManager = NotificationManagerCompat.from(applicationContext)
        notiManager.notify(1, notify)

    }


}

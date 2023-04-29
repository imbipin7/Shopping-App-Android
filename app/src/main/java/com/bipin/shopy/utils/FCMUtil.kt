package com.bipin.shopy.utils

import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging

object FCMUtil {
    private var fcmToken: String? = null
    fun getFcmToken(submitToken: (token: String?) -> Unit) {
        if (fcmToken.isNullOrBlank())
            FirebaseMessaging.getInstance().token
                .addOnCompleteListener { task: Task<String> ->
                    when {
                        task.isSuccessful -> {
                            fcmToken = task.result
                            submitToken(task.result)
                        }
                        else ->
                            submitToken(null)
                    }
                }
        else
            submitToken(fcmToken)
    }
}
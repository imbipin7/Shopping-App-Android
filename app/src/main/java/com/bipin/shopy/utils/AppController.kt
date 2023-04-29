package com.bipin.shopy.utils

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import java.lang.ref.WeakReference

@HiltAndroidApp
class AppController : Application() {
    companion object {
        var mInstance: WeakReference<Context>? = null
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = WeakReference(this.applicationContext)

    }
}
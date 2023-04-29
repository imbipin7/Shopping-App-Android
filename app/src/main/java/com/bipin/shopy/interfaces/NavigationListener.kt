package com.bipin.shopy.interfaces

import android.os.Bundle

interface NavigationListener  {

    fun isLockDrawer(isLock : Boolean)
    fun openDrawer()
    fun getBundle(): Bundle?
}

interface OnUpdateListener{
    fun onUpdated(isUpdated:Boolean)
}
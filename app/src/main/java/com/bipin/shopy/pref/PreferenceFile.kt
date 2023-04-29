package com.bipin.shopy.pref

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class PreferenceFile @Inject constructor(
    private val editor: SharedPreferences.Editor,
    private val sharedPreferences: SharedPreferences
) {
    fun storeKey(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    fun setStringValue(key: String, value: String?) {
        editor.putString(key, value).apply()
    }

    fun storeBoolKey(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun retrieveKey(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun retrieveBoolKey(key: String): Boolean? {
        return sharedPreferences.getBoolean(key, false)
    }

    fun <T> storeObject(key: String, data: T) {
        editor.putString(key, Gson().toJson(data))
        editor.apply()
    }


    fun clearPreference() {
        editor.clear()
        editor.apply()
//    openActivity(Intent(this, Login::class.java), true)
    }

    /*   fun retrieveLoginData(): LoginResponse<*>? {
           return Gson().fromJson(
               sharedPreferences.getString(loginDataKey, "")!!,
               LoginResponse::class.java
           )
       }*/

/*    fun storeLoginData(context: Context, loginResponse: LoginResponse<*>) {
        val prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
        prefsEditor.putString(loginDataKey, Gson().toJson(loginResponse))
        prefsEditor.apply()
        prefsEditor.commit()
    }*/
}
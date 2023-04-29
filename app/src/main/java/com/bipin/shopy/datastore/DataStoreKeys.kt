package com.bipin.shopy.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DataStoreKeys {
    private const val PACKAGE = "com.bipin.androidprojectsetup"
    private const val DATA_STORE_NAME = "${PACKAGE}Shot"

    val THEME_KEY by lazy { stringPreferencesKey("${PACKAGE}THEME_KEY") }
    val LOGIN_DATA by lazy { stringPreferencesKey("${PACKAGE}LOGIN_DATA") }
    val HOME_TITLE by lazy { stringPreferencesKey("${PACKAGE}HOME_TITLE") }
    val HIDE_PROFILE by lazy { booleanPreferencesKey("${PACKAGE}HIDE_PROFILE") }
    val LANGUAGE by lazy { stringPreferencesKey("${PACKAGE}LANGUAGE") }

    val AUTH by lazy { stringPreferencesKey("${PACKAGE}AUTH") }
    val LAT_LONG by lazy { stringPreferencesKey("${PACKAGE}LAT_LONG") }
    val MY_ID by lazy { stringPreferencesKey("${PACKAGE}MY_ID") }
    val MY_IMAGE by lazy { stringPreferencesKey("${PACKAGE}MY_IMAGE") }
    val USER_LOGGED_IN by lazy { booleanPreferencesKey("${PACKAGE}USER_LOGGED_IN") }

    val SENSTIVITY_DATA by lazy { intPreferencesKey("${PACKAGE}SENSTIVITY_DATA") }
    val ECHO_DATA by lazy { intPreferencesKey("${PACKAGE}ECHO_DATA") }
    val START_DELAY_DATA by lazy { stringPreferencesKey("${PACKAGE}START_DELAY_DATA") }
    val PAR_TIME by lazy { stringPreferencesKey("${PACKAGE}PAR_TIME") }

    val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)
}
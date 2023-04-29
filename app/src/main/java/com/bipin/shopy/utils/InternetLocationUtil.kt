package com.bipin.shopy.utils

import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.Geocoder
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.bipin.shopy.MainActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task

object InternetLocationUtil {

    fun getAddressLatLng(postalCode: String?, sendData: (String?, LatLng?, Boolean) -> Unit) {
        if (postalCode.isNullOrBlank())
            sendData(null, null, false)
        else {
            val geocoder = Geocoder(MainActivity.context.get()!!)
            val arrayList = geocoder.getFromLocationName(postalCode, 1)
            if (arrayList.isNullOrEmpty()) {
                Log.e("ADDRESS ->", "NULL")
                sendData(null, null, false)
            } else {
                val latLng = LatLng(arrayList[0].latitude, arrayList[0].longitude)
                val address = arrayList[0].getAddressLine(0)
                Log.e("ADDRESS ->", latLng.toString() + address)
                sendData(address, latLng, true)
            }
        }
    }

    /** Internet Connection Detector */
    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailable(context: Context): Boolean {
        val result: Boolean
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

    /**Check GPS*/
    fun isGPSEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        locationManager?.let {
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }
        return false
    }

    /**Request GPS enable*/
    fun showEnableLocationPopup() {
        val mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval((10 * 1000).toLong())
            .setFastestInterval((1 * 1000).toLong())
        val settingsBuilder = LocationSettingsRequest.Builder()
            .addLocationRequest(mLocationRequest)
        settingsBuilder.setAlwaysShow(true)
        val result: Task<LocationSettingsResponse> =
            LocationServices.getSettingsClient(MainActivity.context.get() as Activity)
                .checkLocationSettings(settingsBuilder.build())
        result.addOnCompleteListener { task ->
            try {
                task.getResult(ApiException::class.java)
            } catch (ex: ApiException) {
                when (ex.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val resolvableApiException = ex as ResolvableApiException
                        resolvableApiException
                            .startResolutionForResult(
                                MainActivity.context.get() as Activity,
                                101
                            )
                    } catch (e: IntentSender.SendIntentException) {
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                    }
                }
            }
        }
    }
}
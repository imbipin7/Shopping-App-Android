package com.bipin.shopy.networkcalls

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.bipin.shopy.MainActivity
import com.bipin.shopy.R
import com.bipin.shopy.datastore.DataStoreUtil
import com.bipin.shopy.utils.Alerts
import com.bipin.shopy.utils.InternetLocationUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(
    private val retrofitApi: RetrofitApi,
    private val dataStore: DataStoreUtil,

    ) {
    @RequiresApi(Build.VERSION_CODES.M)
    fun <T> makeCall(
        loader: Boolean,
        requestProcessor: ApiProcessor<Response<T>>
    ) {
        val activity = MainActivity.context.get()!!

        if (!InternetLocationUtil.isNetworkAvailable(activity)) {
            requestProcessor.onError(activity.getString(R.string.your_device_offline))
            Alerts.showMessage(activity.getString(R.string.your_device_offline))
            return
        }

        if (loader) {
            Alerts.showProgress()
        }

        val dataResponse: Flow<Response<T>> = flow {
            val response =
                requestProcessor.sendRequest(retrofitApi)
            emit(response)
        }.flowOn(Dispatchers.IO)

        CoroutineScope(Dispatchers.Main).launch {
            dataResponse.catch { exception ->
                exception.printStackTrace()
                Alerts.hideProgress()
                Alerts.showMessage(exception.message ?: "")
            }.collect { response ->
                Log.d("TAG", "makeCall: ${response.body()}")
                Log.d("resCodeIs", "====${response.code()}")
                Alerts.hideProgress()
                when {

                    response.code() in 100..199 -> {
                        /**Informational*/
                        requestProcessor.onError(
                            activity.resources?.getString(R.string.some_error_occured) ?: ""
                        )
                        Alerts.showMessage(
                            activity.resources?.getString(R.string.some_error_occured) ?: ""
                        )
                    }
                    response.isSuccessful -> {
                        /**Success*/
                        Log.d("successBody", "====${response.body()}")
                        requestProcessor.onResponse(response)
                    }
                    response.code() in 300..399 -> {
                        /**Redirection*/
                        requestProcessor.onError(
                            activity.resources?.getString(R.string.some_error_occured) ?: ""
                        )
                        Alerts.showMessage(
                            activity.resources?.getString(R.string.some_error_occured) ?: ""
                        )
                    }
                    response.code() == 401 -> {
                        /**UnAuthorized*/
                        Log.d("errorBody", "====${response.errorBody()?.string()}")
                        getRefreshToken()
                        Alerts.sessionExpired()
                        requestProcessor.onError("unAuthorized")
                    }
                    response.code() == 404 -> {
                        val res = response.errorBody()!!.string()
                        val jsonObject = JSONObject(res)
                        Alerts.showMessage(jsonObject.getString("message"))
                        requestProcessor.onError(
                            activity.resources?.getString(R.string.some_error_occured) ?: ""
                        )
                            activity.resources?.getString(R.string.some_error_occured) ?: ""
                    }
                    response.code() in 500..599 -> {
                        /**ServerErrors*/
                        requestProcessor.onError(
                            activity.resources?.getString(R.string.some_error_occured) ?: ""
                        )
                        Alerts.showMessage(
                            activity.resources?.getString(R.string.some_error_occured) ?: ""
                        )

                    }
                    else -> {
                        /**ClientErrors*/
                        val res = response.errorBody()!!.string()
                        val jsonObject = JSONObject(res)
                        when {
                            jsonObject.has("message") -> {
                                requestProcessor.onError(jsonObject.getString("message"))

                                if (!jsonObject.getString("message").equals("Data not found", true))
                                    Alerts.showMessage(jsonObject.getString("message"))
                            }
                            else -> {
                                requestProcessor.onError(
                                    activity.resources?.getString(R.string.some_error_occured)
                                        ?: ""
                                )
                                Alerts.showMessage(
                                    activity.resources?.getString(R.string.some_error_occured)
                                        ?: ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getRefreshToken() {
    }
}

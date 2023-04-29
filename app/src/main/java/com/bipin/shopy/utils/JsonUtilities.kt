package com.bipin.shopy.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File

object JsonUtilities {

    fun getRequestBody(
        jsonObject: JSONObject,
        mediaType: String = "application/json"
    ): RequestBody {
        return jsonObject.toString().toRequestBody(mediaType.toMediaTypeOrNull())
    }

    fun getRequestBody(string: String, mediaType: String = "application/json"): RequestBody {
        return string.toRequestBody(mediaType.toMediaTypeOrNull())
    }

    fun getPartBody(
        file: File,
        param: String,
        mediaType: String = "image/jpg"
    ): MultipartBody.Part? {
        return if (file.path.isNotEmpty()) {
            val newFile = File(file.path)
            val reqFile = newFile.asRequestBody(mediaType.toMediaTypeOrNull())
            MultipartBody.Part.createFormData(param, newFile.name, reqFile)
        } else
            null
    }

   fun getPartBody(
        file: String?,
        param: String,
        mediaType: String = "image/jpg"
    ): MultipartBody.Part? {
        return if (file.isNullOrBlank())
            null
        else try {
            val newFile = File(file)
            val reqFile = newFile.asRequestBody(mediaType.toMediaTypeOrNull())
            MultipartBody.Part.createFormData(param, newFile.name, reqFile)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
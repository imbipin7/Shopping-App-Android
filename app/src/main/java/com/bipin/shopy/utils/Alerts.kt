package com.bipin.shopy.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.bipin.shopy.MainActivity
import com.bipin.shopy.R
import com.bipin.shopy.databinding.ProgressLayoutBinding
import com.google.android.material.snackbar.Snackbar

object Alerts {
    /**Session Expired Alert*/
    fun sessionExpired(message: String? = null) {
        try {
            MainActivity.context.get()?.let { ctx ->
                val aD = AlertDialog.Builder(ctx)
                if (message == null)
                    aD.setTitle(ctx.getString(R.string.session_expired))
                else
                    aD.setTitle(message)
                aD.setCancelable(false)
                aD.setPositiveButton(ctx.getString(R.string.ok)) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    ctx.startActivity(Intent(ctx, MainActivity::class.java).putExtra("data",
                        Bundle().apply {
                            putBoolean("clear", true)
                        }
                    ))
                    (ctx as MainActivity).finishAffinity()
                }
                aD.create()
                aD.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
//    (MainActivity.context.get() as Activity).findViewById(android.R.id.content),

    /**Alert*/
    fun showMessage(
        message: String?,
        actionText: String? = null,
        listener: View.OnClickListener? = null
    ) {
        if (message.isNullOrBlank())
            return
        val snackbar = Snackbar.make(
            (MainActivity.context.get() as Activity).findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_SHORT
        )
        if (!actionText.isNullOrBlank() && listener != null)
            snackbar.setAction(actionText, listener)
        snackbar.show()
        snackbar.duration
    }

    fun showMessage2(
        message: String?,
        actionText: String? = null,
        listener: View.OnClickListener? = null
    ) {
        if (message.isNullOrBlank())
            return
        val snackbar = Snackbar.make(
            (MainActivity.context.get() as Activity).findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_SHORT
        )
        if (!actionText.isNullOrBlank() && listener != null)
            snackbar.setAction(actionText, listener)
        snackbar.show()
        snackbar.duration
    }

    /**Loader*/
    private var customDialog: AlertDialog? = null
    fun showProgress() {
        hideProgress()
        val customAlertBuilder = AlertDialog.Builder(MainActivity.context.get())
        val customAlertView =
            ProgressLayoutBinding.inflate(
                LayoutInflater.from(MainActivity.context.get()),
                null,
                false
            )
        customAlertBuilder.setView(customAlertView.root)
        customAlertBuilder.setCancelable(false)
        customDialog = customAlertBuilder.create()


        customDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog?.show()
    }

    fun hideProgress() {
        if (customDialog != null && customDialog?.isShowing == true) {
            customDialog?.dismiss()
        }
    }

    fun showLog(isShown: Boolean? = true, message: String?, title: String?) {
        if (isShown == true) {
            Log.e("TAG", "$title: $message")
        }
    }
}
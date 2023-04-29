package com.bipin.shopy

import android.app.AlertDialog
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.bipin.shopy.datastore.DataStoreKeys
import com.bipin.shopy.datastore.DataStoreUtil
import com.bipin.shopy.networkcalls.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainVM @Inject constructor(
    private val repository: Repository,
    private val dataStore: DataStoreUtil,
) : ViewModel() {

    val drawerOpen: ObservableField<Boolean> = ObservableField(false)
    private val TAG = "Bipin_Singh"

    lateinit var navController: NavController

    init {
        dataStore.readObject(DataStoreKeys.LOGIN_DATA, Any::class.java) { data ->
//      Set it here
        }


    }



    /** Logout Dialog **/
    private fun showLogout(view: View) {
        val logout = AlertDialog.Builder(view.context)
        logout.setTitle("Are you sure you want to logout?")
        logout.setCancelable(false)
        logout.setPositiveButton("OK") { dialogInterface, _ ->
            dialogInterface.cancel()
            dialogInterface.dismiss()
        }
        logout.setNegativeButton("Cancel") { dialogInterface, _ ->
            dialogInterface.cancel()
            dialogInterface.dismiss()
        }
        logout.create()
        logout.show()
    }



}
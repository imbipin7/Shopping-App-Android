package com.bipin.shopy

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.bipin.shopy.databinding.ActivityMainBinding
import com.bipin.shopy.datastore.DataStoreKeys
import com.bipin.shopy.datastore.DataStoreUtil
import com.bipin.shopy.interfaces.NavigationListener
import com.bipin.shopy.interfaces.OnUpdateListener
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationListener, OnUpdateListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainVM by viewModels()

    companion object {
        lateinit var context: WeakReference<Context>
        lateinit var activity: MainActivity
        var navListener: NavigationListener? = null
        var updateListener: OnUpdateListener? = null
    }

    @Inject
    lateinit var dataStoreUtil: DataStoreUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        context = WeakReference(this)
        binding.viewModel = viewModel
        viewModel.navController = findNavController(R.id.fragmentMain)
        activity = this
        updateListener = this
        navListener = this



        binding?.bottomNav?.setOnItemSelectedListener { item ->
            if (item.itemId != viewModel.navController.currentDestination!!.id) {
                when (item.itemId) {
                    R.id.home -> viewModel.navController.navigate(R.id.home)
                    R.id.wishlist -> viewModel.navController.navigate(R.id.wishlist)
                    R.id.cart -> viewModel.navController.navigate(R.id.cart)
                    R.id.myAccount -> viewModel.navController.navigate(R.id.myAccount)
                }
            }
            true
        }

        viewModel?.navController?.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.home ||
                destination.id == R.id.wishlist ||
                destination.id == R.id.cart ||
                destination.id == R.id.myAccount
            ) {
                binding?.bottomNav?.selectedItemId = destination.id
                binding.bottomNav.isVisible = true
            } else {
                binding.bottomNav.isVisible = false

            }

        }
    }


    override fun isLockDrawer(isLock: Boolean) {
        /*if (isLock) {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }*/
    }

    /***
     * Open drawer listener
     * */

    override fun openDrawer() {
        try {
            /*     if (binding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
                     binding.drawerLayout.closeDrawer(GravityCompat.END)
                 } else {
                     binding.drawerLayout.openDrawer(GravityCompat.END)
                 }*/
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getBundle(): Bundle? {
        return intent.extras
    }

    override fun onUpdated(isUpdated: Boolean) {
        if (isUpdated) {
            dataStoreUtil.readObject(
                DataStoreKeys.LOGIN_DATA,
                Any::class.java
            ) { data ->

            }
        }
    }

}
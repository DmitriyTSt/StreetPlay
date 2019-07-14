package ru.dmitriyt.streetplay.ui.login

import android.Manifest
import android.content.Intent
import android.os.Bundle
import ru.dmitriyt.streetplay.App
import ru.dmitriyt.streetplay.R
import ru.dmitriyt.streetplay.data.storage.Pref
import ru.dmitriyt.streetplay.data.system.PermissionHelper
import ru.dmitriyt.streetplay.ui.global.BaseActivity
import ru.dmitriyt.streetplay.ui.map.MapsActivity
import javax.inject.Inject

class AuthActivity: BaseActivity() {
    override val layoutRes: Int = R.layout.activity_login

    init {
        App.INSTANCE.appComponent.inject(this@AuthActivity)
    }

    @Inject
    lateinit var pref: Pref

    companion object {
        const val PERMISSION_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
    }

    private fun requestPermission() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        val ph = PermissionHelper(this)
        if (ph.checkPermissions(permissions, PERMISSION_REQUEST)) {
            startApp()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            requestPermission()
        }
    }

    private fun startApp() {
        if (pref.userToken != null) {
            startActivity(Intent(this, MapsActivity::class.java))
            finish()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, RegistrationFragment())
                .commit()
        }
    }
}
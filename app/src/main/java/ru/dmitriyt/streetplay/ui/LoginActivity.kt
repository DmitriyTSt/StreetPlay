package ru.dmitriyt.streetplay.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import ru.dmitriyt.streetplay.R
import ru.dmitriyt.streetplay.data.system.PermissionHelper
import ru.dmitriyt.streetplay.ui.global.BaseActivity
import ru.dmitriyt.streetplay.ui.map.MapsActivity

class LoginActivity: BaseActivity() {
    override val layoutRes: Int = R.layout.activity_login

    private val PERMISSION_REQUEST = 1

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
        btn_login.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }
}
package ru.dmitriyt.streetplay.ui.login

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_login.*
import ru.dmitriyt.streetplay.App
import ru.dmitriyt.streetplay.R
import ru.dmitriyt.streetplay.data.system.PermissionHelper
import ru.dmitriyt.streetplay.presentation.login.ILoginView
import ru.dmitriyt.streetplay.presentation.login.LoginPresenter
import ru.dmitriyt.streetplay.ui.global.BaseActivity
import ru.dmitriyt.streetplay.ui.map.MapsActivity
import javax.inject.Inject

class LoginActivity: BaseActivity(), ILoginView {
    override val layoutRes: Int = R.layout.activity_login

    private val PERMISSION_REQUEST = 1

    init {
        App.INSTANCE.appComponent.inject(this@LoginActivity)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
        nickname_edit.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nickname_layout.error = ""
            }
        })
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
            presenter.login(nickname_edit.text.toString())
        }
    }

    override fun showError(message: String?) {
        Snackbar.make(btn_login, message?:"Неизвестная ошибка, попробуйте позже", Snackbar.LENGTH_SHORT).show()
    }

    override fun showEmptyNickname() {
        nickname_layout.error = "Заполните обязательное поле"
    }

    override fun goToMap() {
        startActivity(Intent(this, MapsActivity::class.java))
    }
}
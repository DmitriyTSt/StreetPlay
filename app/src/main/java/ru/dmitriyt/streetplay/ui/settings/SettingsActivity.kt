package ru.dmitriyt.streetplay.ui.settings

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_settings.*
import ru.dmitriyt.streetplay.App
import ru.dmitriyt.streetplay.R
import ru.dmitriyt.streetplay.data.system.addHideErrorOnTextChange
import ru.dmitriyt.streetplay.presentation.settings.ISettingsView
import ru.dmitriyt.streetplay.presentation.settings.SettingsPresenter
import ru.dmitriyt.streetplay.ui.global.BaseActivity
import ru.dmitriyt.streetplay.ui.login.AuthActivity
import javax.inject.Inject

class SettingsActivity: BaseActivity(), ISettingsView {
    override val layoutRes: Int = R.layout.activity_settings

    init {
        App.INSTANCE.appComponent.inject(this@SettingsActivity)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login_edit.addHideErrorOnTextChange(login_layout)
        password_edit.addHideErrorOnTextChange(password_layout)
        btn_save.setOnClickListener {
            presenter.setLogin(login_edit.text.toString(), password_edit.text.toString())
        }
        toolbar.setNavigationOnClickListener {
            finish()
        }
        btn_logout.setOnClickListener {
            presenter.logout()
        }
    }

    override fun successSetLogin() {
        Toast.makeText(this, "Логин для входа успешно установлен", Toast.LENGTH_LONG).show()
    }

    private var progressDialog: ProgressDialog? = null

    override fun setLoading(isLoading: Boolean) {
        progressDialog?.dismiss()
        if (isLoading) {
            progressDialog = ProgressDialog(this)
            progressDialog?.setCancelable(false)
            progressDialog?.show()
        }
    }

    override fun showError(msg: String) {
        Snackbar.make(toolbar, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun showLoginError(msg: String) {
        login_layout.error = msg
    }

    override fun showPasswordError(msg: String) {
        password_layout.error = msg
    }

    override fun successLogout() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}
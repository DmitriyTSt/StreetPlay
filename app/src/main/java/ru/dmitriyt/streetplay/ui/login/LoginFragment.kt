package ru.dmitriyt.streetplay.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_login.*
import ru.dmitriyt.streetplay.App
import ru.dmitriyt.streetplay.R
import ru.dmitriyt.streetplay.data.system.addHideErrorOnTextChange
import ru.dmitriyt.streetplay.presentation.login.ILoginView
import ru.dmitriyt.streetplay.presentation.login.LoginPresenter
import ru.dmitriyt.streetplay.presentation.login.RegistrationPresenter
import ru.dmitriyt.streetplay.ui.global.BaseFragment
import ru.dmitriyt.streetplay.ui.map.MapsActivity
import javax.inject.Inject

class LoginFragment: BaseFragment(), ILoginView {

    override val layoutRes: Int = R.layout.fragment_login

    init {
        App.INSTANCE.appComponent.inject(this@LoginFragment)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_edit.addHideErrorOnTextChange(login_layout)
        password_edit.addHideErrorOnTextChange(password_layout)
        btn_back.setOnClickListener {
            activity?.onBackPressed()
        }
        btn_login.setOnClickListener {
            presenter.login(login_edit.text.toString(), password_edit.text.toString())
        }
    }

    private var progressDialog: ProgressDialog? = null

    override fun setLoading(isLoading: Boolean) {
        progressDialog?.dismiss()
        if (isLoading) {
            progressDialog = ProgressDialog(context)
            progressDialog?.setCancelable(false)
            progressDialog?.show()
        }
    }

    override fun showEmptyLoginError() {
        login_layout.error = "Поле логин не может быть пустым"
    }

    override fun showEmptyPasswordError() {
        password_layout.error = "Поле пароль не может быть пустым"
    }

    override fun goToMap() {
        startActivity(Intent(context, MapsActivity::class.java))
        activity?.finish()
    }

    override fun showError(message: String?) {
        Snackbar.make(btn_login, message?:"Неизвестная ошибка, попробуйте позже", Snackbar.LENGTH_SHORT).show()
    }
}
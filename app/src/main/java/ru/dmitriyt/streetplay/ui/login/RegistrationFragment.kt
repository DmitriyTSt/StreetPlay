package ru.dmitriyt.streetplay.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_registration.*
import ru.dmitriyt.streetplay.App
import ru.dmitriyt.streetplay.R
import ru.dmitriyt.streetplay.data.system.addHideErrorOnTextChange
import ru.dmitriyt.streetplay.presentation.login.IRegistrationView
import ru.dmitriyt.streetplay.presentation.login.RegistrationPresenter
import ru.dmitriyt.streetplay.ui.global.BaseFragment
import ru.dmitriyt.streetplay.ui.map.MapsActivity
import javax.inject.Inject

class RegistrationFragment: BaseFragment(), IRegistrationView {
    override val layoutRes: Int = R.layout.fragment_registration

    init {
        App.INSTANCE.appComponent.inject(this@RegistrationFragment)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: RegistrationPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nickname_edit.addHideErrorOnTextChange(nickname_layout)
        btn_registration.setOnClickListener {
            presenter.login(nickname_edit.text.toString())
        }
        btn_go_to_login.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, LoginFragment())
                ?.addToBackStack(null)
                ?.commit()
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

    override fun showError(message: String?) {
        Snackbar.make(btn_registration, message?:"Неизвестная ошибка, попробуйте позже", Snackbar.LENGTH_SHORT).show()
    }

    override fun showEmptyNickname() {
        nickname_layout.error = "Заполните обязательное поле"
    }

    override fun goToMap() {
        startActivity(Intent(context, MapsActivity::class.java))
        activity?.finish()
    }
}
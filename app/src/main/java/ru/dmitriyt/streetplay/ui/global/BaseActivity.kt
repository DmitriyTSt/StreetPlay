package ru.dmitriyt.streetplay.ui.global

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import com.arellomobile.mvp.MvpAppCompatActivity

abstract class BaseActivity: MvpAppCompatActivity() {
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }
}
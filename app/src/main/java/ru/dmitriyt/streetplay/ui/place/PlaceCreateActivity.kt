package ru.dmitriyt.streetplay.ui.place

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_create_place.*
import pl.aprilapps.easyphotopicker.*
import ru.dmitriyt.streetplay.App
import ru.dmitriyt.streetplay.R
import ru.dmitriyt.streetplay.presentation.place.IPlaceCreateView
import ru.dmitriyt.streetplay.presentation.place.PlaceCreatePresenter
import ru.dmitriyt.streetplay.ui.global.BaseActivity
import java.io.File
import java.lang.Exception
import javax.inject.Inject

class PlaceCreateActivity: BaseActivity(), IPlaceCreateView {
    override val layoutRes = R.layout.activity_create_place

    init {
        App.INSTANCE.appComponent.inject(this@PlaceCreateActivity)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: PlaceCreatePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btn_save.setOnClickListener {
            presenter.savePlace(title_edit.text.toString(), description_edit.text.toString())
        }
        toolbar.setNavigationOnClickListener {
            finish()
        }
        place_image.setOnClickListener {
            EasyImage.openChooserWithGallery(this, "Загрузите фото", 1)
        }
        title_edit.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                title_layout.error = ""
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, object : DefaultCallback() {
            override fun onImagesPicked(imageFiles: MutableList<File>, source: EasyImage.ImageSource?, p2: Int) {
                presenter.uploadImage(imageFiles[0].absolutePath)
            }

            override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) {
                showMessage(e?.message?:"")
            }

        })
    }

    override fun successCreate() {
        Toast.makeText(this, "Площадка успешно принята на модерацию", Toast.LENGTH_LONG).show()
    }

    override fun showImage(path: String) {
        place_image.setImageURI(null)
        place_image.setImageURI(Uri.fromFile(File(path)))
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

    override fun showMessage(msg: String) {
        Snackbar.make(place_image, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun showTitleError(error: String) {
        title_layout.error = error
    }
}
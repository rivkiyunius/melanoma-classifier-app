package com.example.melanomaclassifier.ui.register.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.ui.base.view.BaseActivity
import com.example.melanomaclassifier.ui.main.view.MainActivity
import com.example.melanomaclassifier.ui.register.interactor.RegisterMvpInteractor
import com.example.melanomaclassifier.ui.register.presenter.RegisterMvpPresenter
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RegisterActivity : BaseActivity(), RegisterMvpView {

    @Inject
    lateinit var presenter: RegisterMvpPresenter<RegisterMvpView, RegisterMvpInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setup()
    }

    private fun setup() {
        presenter.onAttach(this)
        validationForm()
        with(button) {
            isEnabled = false
            backgroundResource = R.color.light_grey
            onClick {
                val nama = edt_register_nama.text.toString()
                val email = edt_register_email.text.toString()
                val password = edt_confirm_password.text.toString()
                var deviceId = ""
                FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful){
                        return@OnCompleteListener
                    }

                    deviceId = task.result?.token ?: ""
                    presenter.postDataRegister(nama, email, password, deviceId)
                    Log.d("DEVICE_ID", deviceId)
                })
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun validationForm() {
        RxTextView.afterTextChangeEvents(edt_register_email)
            .skipInitialValue()
            .map {
                edt_register_email.backgroundResource = R.drawable.bg_edit_text
                it.view().text.toString()
            }
            .debounce(1, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
                    edt_register_email.backgroundResource = R.drawable.bg_edit_text_green
                } else {
                    edt_register_email.backgroundResource = R.drawable.bg_edit_text_error
                }
            }

        RxTextView.afterTextChangeEvents(edt_register_password)
            .skipInitialValue()
            .map {
                edt_register_password.backgroundResource = R.drawable.bg_edit_text
                it.view().text.toString()
            }
            .debounce(1, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (edt_register_password.text.length >= 6) {
                    edt_register_password.backgroundResource = R.drawable.bg_edit_text_green
                } else {
                    edt_register_password.backgroundResource = R.drawable.bg_edit_text
                }
            }

        RxTextView.afterTextChangeEvents(edt_confirm_password)
            .map {
                edt_confirm_password.backgroundResource = R.drawable.bg_edit_text
                it.view().text.toString()
            }
            .debounce(1, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (edt_register_password.text.length >= 6
                    && edt_register_email.text.isNotEmpty()
                    && edt_register_nama.text.isNotEmpty()
                    && edt_register_password.text.isNotEmpty()
                ) {
                    if (edt_confirm_password.text.toString() == edt_register_password.text.toString()) {
                        edt_confirm_password.backgroundResource = R.drawable.bg_edit_text_green
                        with(button){
                            isEnabled = true
                            backgroundResource = R.color.sky_blue_color
                        }
                    } else {
                        edt_confirm_password.backgroundResource = R.drawable.bg_edit_text_error
                        with(button){
                            isEnabled = false
                            backgroundResource = R.color.light_grey
                        }
                    }
                }
            }
    }

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String) {}

    override fun openHome() {
        startActivity<MainActivity>()
    }
}
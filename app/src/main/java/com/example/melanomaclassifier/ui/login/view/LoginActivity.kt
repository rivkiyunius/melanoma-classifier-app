package com.example.melanomaclassifier.ui.login.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.ui.base.view.BaseActivity
import com.example.melanomaclassifier.ui.login.interactor.LoginMvpInteractor
import com.example.melanomaclassifier.ui.login.presenter.LoginMvpPresenter
import com.example.melanomaclassifier.ui.main.view.MainActivity
import com.example.melanomaclassifier.ui.register.view.RegisterActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.*
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.NoSuchElementException

class LoginActivity : BaseActivity(), LoginMvpView {

    @Inject
    lateinit var presenter: LoginMvpPresenter<LoginMvpView, LoginMvpInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setup()
    }

    private fun setup() {
        validationForm()
        openRegister()
        presenter.onAttach(this)
        with(btn_login){
            isEnabled = false
            backgroundResource = R.color.light_grey
            var deviceId = ""
            setOnClickListener {
                val email = edt_email.text.toString()
                val password = edt_password.text.toString()
                FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener {task ->
                    if (!task.isSuccessful){
                        return@OnCompleteListener
                    }

                    deviceId = task.result?.token ?: ""
                    presenter.login(email, password, deviceId)
                    Log.d("DEVICE_ID", deviceId)
                })
            }
        }
    }

    private fun openRegister() {
        tv_register.setOnClickListener {
            startActivity<RegisterActivity>()
        }
    }

    @SuppressLint("CheckResult")
    private fun validationForm() {
        RxTextView.afterTextChangeEvents(edt_email)
            .skipInitialValue()
            .map {
                tl_login_email.error = null
                it.view().text.toString()
            }
            .debounce(1, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                if(Patterns.EMAIL_ADDRESS.matcher(it).matches()){
                    edt_email.backgroundResource = R.drawable.bg_edit_text_green
                }else{
                    edt_email.backgroundResource = R.drawable.bg_edit_text_error
                }
            }

        RxTextView.afterTextChangeEvents(edt_password)
            .skipInitialValue()
            .map {
                edt_password.backgroundResource = R.drawable.bg_edit_text
                tl_login_password.error = null
                it.view().text.toString()
            }
            .debounce(1, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                if(edt_password.text.length >= 6 && edt_email.text.isNotEmpty()){
                    edt_password.backgroundResource = R.drawable.bg_edit_text_green
                    with(btn_login){
                        isEnabled = true
                        backgroundResource = R.color.sky_blue_color
                    }
                }else{
                    edt_password.backgroundResource = R.drawable.bg_edit_text
                    with(btn_login){
                        isEnabled = false
                        backgroundResource = R.color.light_grey
                    }
                }
            }
    }

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String) {}

    override fun loginSuccess() {
        startActivity(intentFor<MainActivity>().newTask().clearTask())
    }
}
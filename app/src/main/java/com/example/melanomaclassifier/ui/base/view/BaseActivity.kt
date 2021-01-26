package com.example.melanomaclassifier.ui.base.view

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.melanomaclassifier.ui.login.view.LoginActivity
import com.example.melanomaclassifier.util.CommonUtil
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.app_bar.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

abstract class BaseActivity : AppCompatActivity(), MvpView, BaseFragment.CallBack {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
    }

    override fun hideLoading() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }

    protected fun setupToolbar(title: String = "") {
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowCustomEnabled(true)
    }

    override fun showLoading() {
        hideLoading()
        progressDialog = CommonUtil.showLoadingDialog(this)
    }

    override fun showMessage(message: String?) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    protected fun setFullScreenMode() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun showMessage(resId: Int) {
        Toast.makeText(applicationContext, getString(resId), Toast.LENGTH_SHORT).show()
    }

    private fun performDI() = AndroidInjection.inject(this)

    override fun onTokenExpired() {
        startActivity(intentFor<LoginActivity>().newTask().clearTask())
    }
}
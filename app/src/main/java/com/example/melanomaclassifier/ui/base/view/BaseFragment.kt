package com.example.melanomaclassifier.ui.base.view

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.melanomaclassifier.ui.login.view.LoginActivity
import com.example.melanomaclassifier.util.CommonUtil
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.support.v4.intentFor

abstract class BaseFragment : Fragment(), MvpView{
    private var parentActivity: BaseActivity? = null
    private var progressDialog: ProgressDialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is BaseActivity){
            val activity = context as BaseActivity?
            this.parentActivity = activity
            activity?.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun hideLoading() {
        if(progressDialog != null && progressDialog?.isShowing!!){
            progressDialog?.cancel()
        }
    }

    override fun showLoading() {
        hideLoading()
        progressDialog = CommonUtil.showLoadingDialog(this.context)
    }

    override fun showMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(resId: Int) {
        Toast.makeText(context, getString(resId), Toast.LENGTH_SHORT).show()
    }

    override fun onTokenExpired() {
        startActivity(intentFor<LoginActivity>().newTask().clearTask())
    }

    private fun performDI() = AndroidSupportInjection.inject(this)

    interface CallBack{
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }

    abstract fun setUp()
}
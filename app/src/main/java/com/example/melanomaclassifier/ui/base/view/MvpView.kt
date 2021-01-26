package com.example.melanomaclassifier.ui.base.view

import androidx.annotation.StringRes
import retrofit2.HttpException

interface MvpView {
    fun showMessage(message: String?)
    fun showMessage(@StringRes resId: Int)
    fun showLoading()
    fun hideLoading()
    fun onTokenExpired()
}
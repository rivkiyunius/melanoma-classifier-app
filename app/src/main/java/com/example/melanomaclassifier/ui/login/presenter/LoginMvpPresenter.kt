package com.example.melanomaclassifier.ui.login.presenter

import com.example.melanomaclassifier.ui.base.presenter.MvpPresenter
import com.example.melanomaclassifier.ui.login.interactor.LoginMvpInteractor
import com.example.melanomaclassifier.ui.login.view.LoginMvpView

interface LoginMvpPresenter<V: LoginMvpView, I: LoginMvpInteractor>: MvpPresenter<V, I> {
    fun login(username: String, password: String, deviceId: String)
}
package com.example.melanomaclassifier.ui.register.presenter

import com.example.melanomaclassifier.ui.base.presenter.MvpPresenter
import com.example.melanomaclassifier.ui.register.interactor.RegisterMvpInteractor
import com.example.melanomaclassifier.ui.register.view.RegisterMvpView

interface RegisterMvpPresenter<V : RegisterMvpView, I : RegisterMvpInteractor> : MvpPresenter<V, I> {
    fun postDataRegister(nama: String, email: String, password: String, deviceId: String)
}
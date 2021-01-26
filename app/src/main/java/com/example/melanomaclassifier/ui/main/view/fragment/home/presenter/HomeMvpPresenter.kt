package com.example.melanomaclassifier.ui.main.view.fragment.home.presenter

import com.example.melanomaclassifier.ui.base.presenter.MvpPresenter
import com.example.melanomaclassifier.ui.main.view.fragment.home.interactor.HomeMvpInteractor
import com.example.melanomaclassifier.ui.main.view.fragment.home.view.HomeMvpView

interface HomeMvpPresenter<V: HomeMvpView, I: HomeMvpInteractor>: MvpPresenter<V, I> {
    fun showUser()
    fun showPeriksaPasien()
    fun logout()
}
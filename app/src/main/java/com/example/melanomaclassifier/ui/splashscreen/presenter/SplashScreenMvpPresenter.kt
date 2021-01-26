package com.example.melanomaclassifier.ui.splashscreen.presenter

import com.example.melanomaclassifier.ui.base.presenter.MvpPresenter
import com.example.melanomaclassifier.ui.splashscreen.interactor.SplashScreenMvpInteractor
import com.example.melanomaclassifier.ui.splashscreen.view.SplashScreenMvpView

interface SplashScreenMvpPresenter<V : SplashScreenMvpView, I : SplashScreenMvpInteractor> :
    MvpPresenter<V, I> {

    fun decidePage()
}
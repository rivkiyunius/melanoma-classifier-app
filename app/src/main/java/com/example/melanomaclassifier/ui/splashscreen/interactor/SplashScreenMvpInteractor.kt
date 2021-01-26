package com.example.melanomaclassifier.ui.splashscreen.interactor

import com.example.melanomaclassifier.ui.base.interactor.MvpInteractor
import com.example.melanomaclassifier.ui.base.view.MvpView

interface SplashScreenMvpInteractor: MvpInteractor {

    fun hasLoggedIn(): Boolean
}
package com.example.melanomaclassifier.ui.splashscreen

import com.example.melanomaclassifier.ui.splashscreen.interactor.SplashScreenInteractor
import com.example.melanomaclassifier.ui.splashscreen.interactor.SplashScreenMvpInteractor
import com.example.melanomaclassifier.ui.splashscreen.presenter.SplashScreenMvpPresenter
import com.example.melanomaclassifier.ui.splashscreen.presenter.SplashScreenPresenter
import com.example.melanomaclassifier.ui.splashscreen.view.SplashScreenMvpView
import dagger.Module
import dagger.Provides

@Module
class SplashScreenActivityModule {
    @Provides
    fun provideSplashScreenInteractor(interactor: SplashScreenInteractor): SplashScreenMvpInteractor =
        interactor

    @Provides
    fun provideSplashScreenPresenter(presenter: SplashScreenPresenter<SplashScreenMvpView, SplashScreenInteractor>): SplashScreenMvpPresenter<SplashScreenMvpView, SplashScreenInteractor> =
        presenter
}
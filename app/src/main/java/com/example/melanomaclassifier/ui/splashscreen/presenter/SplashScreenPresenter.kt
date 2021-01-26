package com.example.melanomaclassifier.ui.splashscreen.presenter

import android.util.Log
import com.example.melanomaclassifier.ui.base.presenter.BasePresenter
import com.example.melanomaclassifier.ui.splashscreen.interactor.SplashScreenMvpInteractor
import com.example.melanomaclassifier.ui.splashscreen.view.SplashScreenMvpView
import com.example.melanomaclassifier.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashScreenPresenter<V : SplashScreenMvpView, I : SplashScreenMvpInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = compositeDisposable
), SplashScreenMvpPresenter<V, I> {
    override fun decidePage() {
        interactor?.let {
            when (it.hasLoggedIn()) {
                true -> getView()?.openMainPage()
                false -> getView()?.openLoginPage()
            }
        }
    }
}
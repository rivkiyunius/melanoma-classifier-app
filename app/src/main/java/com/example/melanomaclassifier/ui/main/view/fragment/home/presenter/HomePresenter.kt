package com.example.melanomaclassifier.ui.main.view.fragment.home.presenter

import android.util.Log
import com.example.melanomaclassifier.ui.base.presenter.BasePresenter
import com.example.melanomaclassifier.ui.main.view.fragment.home.interactor.HomeMvpInteractor
import com.example.melanomaclassifier.ui.main.view.fragment.home.view.HomeMvpView
import com.example.melanomaclassifier.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import javax.inject.Inject

class HomePresenter<V : HomeMvpView, I : HomeMvpInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = disposable
), HomeMvpPresenter<V, I> {
    override fun showUser() {
        interactor?.let {
            compositeDisposable.add(
                it.getUser()
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .doOnSubscribe { getView()?.showLoading() }
                    .doOnTerminate { getView()?.hideLoading() }
                    .subscribe({ response ->
                        if (response.isSuccessfull()) {
                            getView()?.showUser(response.result)
                        } else {
                            getView()?.showMessage(response.error)
                        }
                    }, { e ->
                        when (e) {
                            is HttpException -> handleApiError(e)
                            else -> handleGenericError(e.localizedMessage)
                        }
                    })
            )
        }
    }

    override fun showPeriksaPasien() {
        interactor?.let {
            compositeDisposable.add(
                it.getPeriksaPasien()
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .doOnSubscribe { getView()?.showLoading() }
                    .doOnTerminate { getView()?.hideLoading() }
                    .subscribe({ response ->
                        if (response.isSuccessfull()) {
                            getView()?.showPeriksaPasien(response.result)
                        } else {
                            getView()?.showMessage(response.error)
                        }
                    }, { e ->
                        when (e) {
                            is HttpException -> handleApiError(e)
                            else -> handleGenericError(e.localizedMessage)
                        }
                    })
            )
        }
    }

    override fun logout() {
        interactor?.let {
            it.logout()
            getView()?.redirectToLogin()
        }
    }
}
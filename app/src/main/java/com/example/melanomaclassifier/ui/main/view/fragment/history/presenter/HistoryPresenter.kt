package com.example.melanomaclassifier.ui.main.view.fragment.history.presenter

import com.example.melanomaclassifier.ui.base.presenter.BasePresenter
import com.example.melanomaclassifier.ui.main.view.fragment.history.interactor.HistoryInteractor
import com.example.melanomaclassifier.ui.main.view.fragment.history.interactor.HistoryMvpInteractor
import com.example.melanomaclassifier.ui.main.view.fragment.history.view.HistoryMvpView
import com.example.melanomaclassifier.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import javax.inject.Inject

class HistoryPresenter<V : HistoryMvpView, I : HistoryMvpInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = compositeDisposable
), HistoryMvpPresenter<V, I> {
    override fun getHistory() {
        interactor?.let {
            compositeDisposable.add(
                it.getHistoryPeriksa()
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .doOnSubscribe { getView()?.showLoading() }
                    .doOnTerminate { getView()?.hideLoading() }
                    .subscribe({ response ->
                        if (response.isSuccessfull()) {
                            getView()?.showHistory(response.result)
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
}
package com.example.melanomaclassifier.ui.detail_periksa.presenter

import com.example.melanomaclassifier.ui.base.presenter.BasePresenter
import com.example.melanomaclassifier.ui.detail_periksa.interactor.DetailPeriksaMvpInteractor
import com.example.melanomaclassifier.ui.detail_periksa.view.DetailPeriksaMvpView
import com.example.melanomaclassifier.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import javax.inject.Inject

class DetailPeriksaPresenter<V : DetailPeriksaMvpView, I : DetailPeriksaMvpInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = compositeDisposable
), DetailPeriksaMvpPresenter<V, I> {
    override fun getDetailPeriksa(id: Int) {
        interactor?.let {
            it.getDetailPeriksa(id)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .doOnSubscribe { getView()?.showLoading() }
                .doOnTerminate { getView()?.hideLoading() }
                .subscribe({ response ->
                    if (response.isSuccessfull()) {
                        getView()?.showDetail(response.result)
                    }
                }, { e ->
                    when (e) {
                        is HttpException -> handleApiError(e)
                        else -> handleGenericError(e.localizedMessage)
                    }
                })
        }
    }
}
package com.example.melanomaclassifier.ui.rate_dokter.presenter

import android.util.Log
import com.example.melanomaclassifier.data.network.request.RatingDokterRequest
import com.example.melanomaclassifier.ui.base.presenter.BasePresenter
import com.example.melanomaclassifier.ui.rate_dokter.interactor.RateDokterMvpInteractor
import com.example.melanomaclassifier.ui.rate_dokter.view.RateDokterMvpView
import com.example.melanomaclassifier.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import javax.inject.Inject

class RateDokterPresenter<V : RateDokterMvpView, I : RateDokterMvpInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = compositeDisposable
), RateDokterMvpPresenter<V, I> {
    override fun postRating(rating: Int, notes: String, idDokter: Int) {
        val request = RatingDokterRequest()
        request.rate = rating
        request.note = notes
        request.dokterId = idDokter

        interactor?.let {
            compositeDisposable.add(
                it.postRating(request)
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .doOnSubscribe { getView()?.showLoading() }
                    .doOnTerminate { getView()?.hideLoading() }
                    .subscribe({ response ->
                        if (response.isSuccessfull()) {
                            getView()?.redirectToHome()
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

    override fun loadDataDokter(id: Int) {
        interactor?.let {
            compositeDisposable.add(
                it.getDataDoctor(id)
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .doOnSubscribe { getView()?.showLoading() }
                    .doOnTerminate { getView()?.hideLoading() }
                    .subscribe({ response ->
                        if (response.isSuccessfull()) {
                            getView()?.loadDataDokter(response.result)
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
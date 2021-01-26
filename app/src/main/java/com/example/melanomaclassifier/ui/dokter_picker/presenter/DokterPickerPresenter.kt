package com.example.melanomaclassifier.ui.dokter_picker.presenter

import com.example.melanomaclassifier.ui.base.presenter.BasePresenter
import com.example.melanomaclassifier.ui.dokter_picker.interactor.DokterPickerMvpInteractor
import com.example.melanomaclassifier.ui.dokter_picker.ui.DokterPickerMvpView
import com.example.melanomaclassifier.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import javax.inject.Inject

class DokterPickerPresenter<V : DokterPickerMvpView, I : DokterPickerMvpInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = compositeDisposable
), DokterPickerMvpPresenter<V, I> {
    override fun getDataDokter() {
        interactor?.let {
            compositeDisposable.add(
                it.getDokter()
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .doOnSubscribe { getView()?.showLoading() }
                    .doOnTerminate { getView()?.hideLoading() }
                    .subscribe({ response ->
                        if(response.isSuccessfull()){
                            getView()?.showDataDokter(response.result)
                        }
                    }, {e->
                        when (e) {
                            is HttpException -> handleApiError(e)
                            else -> handleGenericError(e.localizedMessage)
                        }
                    })
            )
        }
    }
}
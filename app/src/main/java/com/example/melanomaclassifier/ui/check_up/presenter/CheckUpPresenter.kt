package com.example.melanomaclassifier.ui.check_up.presenter

import android.util.Log
import com.example.melanomaclassifier.data.network.request.CheckUpRequest
import com.example.melanomaclassifier.ui.base.presenter.BasePresenter
import com.example.melanomaclassifier.ui.check_up.interactor.CheckUpMvpInteractor
import com.example.melanomaclassifier.ui.check_up.view.CheckUpMvpView
import com.example.melanomaclassifier.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.io.File
import javax.inject.Inject

class CheckUpPresenter<V : CheckUpMvpView, I : CheckUpMvpInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = compositeDisposable
), CheckUpMvpPresenter<V, I> {
    override fun checkUp(fileUpload: File?, keluhan: String, idDokter: String) {
        val request = CheckUpRequest()
        request.file = fileUpload
        request.keluhan = keluhan
        request.idDokter = idDokter

        interactor?.let {
            compositeDisposable.add(
                it.periksaMelanoma(request)
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
}
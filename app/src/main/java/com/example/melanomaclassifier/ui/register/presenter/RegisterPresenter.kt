package com.example.melanomaclassifier.ui.register.presenter

import com.example.melanomaclassifier.data.network.request.RegisterRequest
import com.example.melanomaclassifier.ui.base.presenter.BasePresenter
import com.example.melanomaclassifier.ui.register.interactor.RegisterInteractor
import com.example.melanomaclassifier.ui.register.interactor.RegisterMvpInteractor
import com.example.melanomaclassifier.ui.register.view.RegisterMvpView
import com.example.melanomaclassifier.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import javax.inject.Inject

class RegisterPresenter<V : RegisterMvpView, I : RegisterMvpInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = disposable
), RegisterMvpPresenter<V, I> {
    override fun postDataRegister(nama: String, email: String, password: String, deviceId: String) {
        val request = RegisterRequest()
        request.nama = nama
        request.email = email
        request.password = password
        request.deviceId = deviceId

        interactor?.let {
            compositeDisposable.add(
                it.register(request)
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .doOnSubscribe { getView()?.showLoading() }
                    .doOnTerminate { getView()?.hideLoading() }
                    .subscribe({response->
                        if(response.isSuccessfull()){
                            it.setToken(response.result.token)
                            getView()?.openHome()
                        }
                    }, {e->
                        when(e){
                            is HttpException -> handleApiError(e)
                            else -> handleGenericError(e.localizedMessage)
                        }
                    })
            )
        }
    }

}
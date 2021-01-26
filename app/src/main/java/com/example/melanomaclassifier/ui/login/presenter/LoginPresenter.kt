package com.example.melanomaclassifier.ui.login.presenter

import android.util.Log
import com.example.melanomaclassifier.data.network.request.LoginRequest
import com.example.melanomaclassifier.ui.base.presenter.BasePresenter
import com.example.melanomaclassifier.ui.login.interactor.LoginMvpInteractor
import com.example.melanomaclassifier.ui.login.view.LoginMvpView
import com.example.melanomaclassifier.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class LoginPresenter<V : LoginMvpView, I : LoginMvpInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = disposable
), LoginMvpPresenter<V, I> {
    override fun login(username: String, password: String, deviceId: String) {

        val reqLogin = LoginRequest()
        reqLogin.email = username
        reqLogin.password = password
        reqLogin.deviceId = deviceId

        interactor?.let {
            compositeDisposable.add(
                it.login(reqLogin)
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .doOnSubscribe { getView()?.showLoading() }
                    .doOnTerminate { getView()?.hideLoading() }
                    .subscribe({ response ->
                        if (response.isSuccessfull()) {
                            getView()?.loginSuccess()
                            it.setToken(response.result.jwt_token)
                        } else {
                            getView()?.showMessage(response.error)
                        }
                    }, { e ->
                        when (e) {
                            is HttpException -> handleApiError(e)
                            is UnknownHostException -> {
                                getView()?.showMessage("No Internet Connection")
                            }
                            else -> {
                                handleGenericError(e.localizedMessage)
                                Log.e("ERROR", e.localizedMessage)
                            }
                        }
                    })
            )
        }
    }

}
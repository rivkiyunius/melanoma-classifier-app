package com.example.melanomaclassifier.ui.base.presenter

import android.util.Log
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.data.network.response.ErrorResponse
import com.example.melanomaclassifier.ui.base.interactor.MvpInteractor
import com.example.melanomaclassifier.ui.base.view.MvpView
import com.example.melanomaclassifier.util.AppConstants
import com.example.melanomaclassifier.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException

abstract class BasePresenter<V : MvpView, I : MvpInteractor> internal constructor(
    protected var interactor: I?,
    protected val schedulerProvider: SchedulerProvider,
    protected val compositeDisposable: CompositeDisposable
) : MvpPresenter<V, I> {
    private var view: V? = null
    private val isAttachedView: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }

    override fun handleApiError(error: HttpException) {
        if (error.code() == AppConstants.ApiStatusCode.BAD_REQUEST ||
            error.code() == AppConstants.ApiStatusCode.FORBIDDEN ||
            error.code() == AppConstants.ApiStatusCode.UNAUTHORIZED ||
            error.code() == AppConstants.ApiStatusCode.NOT_FOUND
        ) {
            if (interactor?.isUserLoggedIn()!!) {
                performUserLogout()
                return
            }
        }

        val errorMessage = ErrorResponse.fromRaw(error.response()?.errorBody()?.string()!!).message

        if (errorMessage != null) {
            view?.showMessage(errorMessage)
        } else {
            view?.showMessage("Something went wrong")
        }
        Log.e("ERROR", error.localizedMessage)
    }

    override fun handleGenericError(errorMessage: String?) {
        errorMessage?.let {
            view?.showMessage(it)
        }
    }

    override fun performUserLogout() {
        interactor?.let {
            it.performUserLogout()
            getView()?.onTokenExpired()
        }
    }
}
package com.example.melanomaclassifier.ui.main.presenter

import com.example.melanomaclassifier.ui.base.presenter.BasePresenter
import com.example.melanomaclassifier.ui.main.interactor.MainMvpInteractor
import com.example.melanomaclassifier.ui.main.view.MainMvpView
import com.example.melanomaclassifier.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter<V: MainMvpView, I: MainMvpInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
): BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = disposable
), MainMvpPresenter<V, I>{

    override fun onAttach(view: V?) {

    }

    override fun onDetach() {

    }
}
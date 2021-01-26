package com.example.melanomaclassifier.ui.main.presenter

import com.example.melanomaclassifier.ui.base.presenter.MvpPresenter
import com.example.melanomaclassifier.ui.main.interactor.MainMvpInteractor
import com.example.melanomaclassifier.ui.main.view.MainMvpView

interface MainMvpPresenter<V : MainMvpView, I : MainMvpInteractor> : MvpPresenter<V, I> {
}
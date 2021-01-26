package com.example.melanomaclassifier.ui.main.view.fragment.history.presenter

import com.example.melanomaclassifier.ui.base.presenter.MvpPresenter
import com.example.melanomaclassifier.ui.main.view.fragment.history.interactor.HistoryMvpInteractor
import com.example.melanomaclassifier.ui.main.view.fragment.history.view.HistoryMvpView

interface HistoryMvpPresenter<V : HistoryMvpView, I : HistoryMvpInteractor> : MvpPresenter<V, I> {
    fun getHistory()
}
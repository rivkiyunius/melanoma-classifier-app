package com.example.melanomaclassifier.ui.main.view.fragment.history.view

import com.example.melanomaclassifier.data.network.response.ListPeriksaResponse
import com.example.melanomaclassifier.ui.base.view.MvpView

interface HistoryMvpView: MvpView {
    fun showHistory(data: ListPeriksaResponse)
}
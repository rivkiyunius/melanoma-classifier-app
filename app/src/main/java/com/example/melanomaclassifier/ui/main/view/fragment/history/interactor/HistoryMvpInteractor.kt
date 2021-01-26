package com.example.melanomaclassifier.ui.main.view.fragment.history.interactor

import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.ListPeriksaResponse
import com.example.melanomaclassifier.ui.base.interactor.MvpInteractor
import io.reactivex.Observable

interface HistoryMvpInteractor: MvpInteractor {
    fun getHistoryPeriksa(): Observable<BaseResponse<ListPeriksaResponse>>
}
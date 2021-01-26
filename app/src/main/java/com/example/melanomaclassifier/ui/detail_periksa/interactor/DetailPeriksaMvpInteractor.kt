package com.example.melanomaclassifier.ui.detail_periksa.interactor

import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.DetailPeriksaResponse
import com.example.melanomaclassifier.ui.base.interactor.MvpInteractor
import io.reactivex.Observable

interface DetailPeriksaMvpInteractor : MvpInteractor {
    fun getDetailPeriksa(id: Int): Observable<BaseResponse<DetailPeriksaResponse>>
}
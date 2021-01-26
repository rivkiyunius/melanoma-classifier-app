package com.example.melanomaclassifier.ui.main.view.fragment.home.interactor

import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.GetUserResponse
import com.example.melanomaclassifier.data.network.response.ListPeriksaResponse
import com.example.melanomaclassifier.ui.base.interactor.MvpInteractor
import io.reactivex.Observable

interface HomeMvpInteractor : MvpInteractor{
    fun getUser(): Observable<BaseResponse<GetUserResponse>>
    fun getPeriksaPasien(): Observable<BaseResponse<ListPeriksaResponse>>
    fun logout()
}
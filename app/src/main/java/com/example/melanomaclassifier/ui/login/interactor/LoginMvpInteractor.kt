package com.example.melanomaclassifier.ui.login.interactor

import com.example.melanomaclassifier.data.network.request.LoginRequest
import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.LoginResponse
import com.example.melanomaclassifier.ui.base.interactor.MvpInteractor
import io.reactivex.Observable

interface LoginMvpInteractor : MvpInteractor {
    fun login(request: LoginRequest): Observable<BaseResponse<LoginResponse>>

    fun setToken(token: String)
}
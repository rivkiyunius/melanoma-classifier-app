package com.example.melanomaclassifier.ui.register.interactor

import com.example.melanomaclassifier.data.network.request.RegisterRequest
import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.RegisterResponse
import com.example.melanomaclassifier.ui.base.interactor.MvpInteractor
import io.reactivex.Observable

interface RegisterMvpInteractor : MvpInteractor {
    fun register(request: RegisterRequest): Observable<BaseResponse<RegisterResponse>>
    fun setToken(token: String)
}
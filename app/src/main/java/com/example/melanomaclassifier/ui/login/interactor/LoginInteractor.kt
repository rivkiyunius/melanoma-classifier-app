package com.example.melanomaclassifier.ui.login.interactor

import com.example.melanomaclassifier.data.network.ApiHelper
import com.example.melanomaclassifier.data.network.request.LoginRequest
import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.LoginResponse
import com.example.melanomaclassifier.data.preferences.PreferencesHelper
import com.example.melanomaclassifier.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class LoginInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    apiHelper: ApiHelper
) : BaseInteractor(preferencesHelper, apiHelper), LoginMvpInteractor {
    override fun login(request: LoginRequest): Observable<BaseResponse<LoginResponse>> = apiHelper.login(request)
    override fun setToken(token: String) {
        preferencesHelper.setToken(token)
    }
}
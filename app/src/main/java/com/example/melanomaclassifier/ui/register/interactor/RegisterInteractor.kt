package com.example.melanomaclassifier.ui.register.interactor

import com.example.melanomaclassifier.data.network.ApiHelper
import com.example.melanomaclassifier.data.network.request.RegisterRequest
import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.RegisterResponse
import com.example.melanomaclassifier.data.preferences.PreferencesHelper
import com.example.melanomaclassifier.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class RegisterInteractor @Inject internal constructor(
    preferenceHelper: PreferencesHelper,
    apiHelper: ApiHelper
) : BaseInteractor(preferenceHelper, apiHelper), RegisterMvpInteractor {
    override fun register(request: RegisterRequest): Observable<BaseResponse<RegisterResponse>> = apiHelper.register(request)

    override fun setToken(token: String) {
        preferencesHelper.setToken(token)
    }
}
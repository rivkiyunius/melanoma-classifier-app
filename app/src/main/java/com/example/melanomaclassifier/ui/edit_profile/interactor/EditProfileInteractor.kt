package com.example.melanomaclassifier.ui.edit_profile.interactor

import com.example.melanomaclassifier.data.network.ApiHelper
import com.example.melanomaclassifier.data.network.request.EditProfileRequest
import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.GetUserResponse
import com.example.melanomaclassifier.data.network.response.RegisterResponse
import com.example.melanomaclassifier.data.preferences.PreferencesHelper
import com.example.melanomaclassifier.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class EditProfileInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    apiHelper: ApiHelper
) : BaseInteractor(preferencesHelper, apiHelper), EditProfileMvpInteractor {
    override fun postUpdate(request: EditProfileRequest): Observable<BaseResponse<RegisterResponse>> = apiHelper.update(request)
    override fun loadDataUser(): Observable<BaseResponse<GetUserResponse>> = apiHelper.getUser()
}
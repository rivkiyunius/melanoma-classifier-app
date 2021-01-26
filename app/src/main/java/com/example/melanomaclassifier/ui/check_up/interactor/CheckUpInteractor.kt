package com.example.melanomaclassifier.ui.check_up.interactor

import com.example.melanomaclassifier.data.network.ApiHelper
import com.example.melanomaclassifier.data.network.request.CheckUpRequest
import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.CheckUpResponse
import com.example.melanomaclassifier.data.network.response.GetDataDoctorsResponse
import com.example.melanomaclassifier.data.preferences.PreferencesHelper
import com.example.melanomaclassifier.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class CheckUpInteractor @Inject constructor(
    preferencesHelper: PreferencesHelper,
    apiHelper: ApiHelper
) : BaseInteractor(preferencesHelper, apiHelper), CheckUpMvpInteractor {
    override fun periksaMelanoma(request: CheckUpRequest): Observable<BaseResponse<CheckUpResponse>> = apiHelper.checkUp(request)

}
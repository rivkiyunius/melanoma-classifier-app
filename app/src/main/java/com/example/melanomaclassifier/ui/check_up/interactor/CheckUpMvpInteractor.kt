package com.example.melanomaclassifier.ui.check_up.interactor

import com.example.melanomaclassifier.data.network.request.CheckUpRequest
import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.CheckUpResponse
import com.example.melanomaclassifier.data.network.response.GetDataDoctorsResponse
import com.example.melanomaclassifier.ui.base.interactor.MvpInteractor
import io.reactivex.Observable

interface CheckUpMvpInteractor: MvpInteractor {
    fun periksaMelanoma(request: CheckUpRequest): Observable<BaseResponse<CheckUpResponse>>
}
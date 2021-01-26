package com.example.melanomaclassifier.ui.edit_profile.interactor

import com.example.melanomaclassifier.data.network.request.EditProfileRequest
import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.GetUserResponse
import com.example.melanomaclassifier.data.network.response.RegisterResponse
import com.example.melanomaclassifier.ui.base.interactor.MvpInteractor
import io.reactivex.Observable

interface EditProfileMvpInteractor: MvpInteractor {
    fun postUpdate(request: EditProfileRequest): Observable<BaseResponse<RegisterResponse>>
    fun loadDataUser(): Observable<BaseResponse<GetUserResponse>>
}
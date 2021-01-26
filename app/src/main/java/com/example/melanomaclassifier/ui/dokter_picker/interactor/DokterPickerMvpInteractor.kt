package com.example.melanomaclassifier.ui.dokter_picker.interactor

import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.DokterModelResponse
import com.example.melanomaclassifier.data.network.response.GetDataDoctorsResponse
import com.example.melanomaclassifier.ui.base.interactor.MvpInteractor
import io.reactivex.Observable

interface DokterPickerMvpInteractor : MvpInteractor{
    fun getDokter(): Observable<BaseResponse<DokterModelResponse>>
}
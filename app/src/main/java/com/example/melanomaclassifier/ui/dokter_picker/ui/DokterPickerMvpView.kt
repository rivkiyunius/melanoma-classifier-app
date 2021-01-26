package com.example.melanomaclassifier.ui.dokter_picker.ui

import com.example.melanomaclassifier.data.network.response.DokterModelResponse
import com.example.melanomaclassifier.data.network.response.GetDataDoctorsResponse
import com.example.melanomaclassifier.ui.base.view.MvpView

interface DokterPickerMvpView: MvpView {
    fun showDataDokter(data: DokterModelResponse)
}
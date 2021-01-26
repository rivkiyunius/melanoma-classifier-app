package com.example.melanomaclassifier.ui.rate_dokter.view

import com.example.melanomaclassifier.data.network.response.DataDoctors
import com.example.melanomaclassifier.data.network.response.DataDokterResponse
import com.example.melanomaclassifier.ui.base.view.MvpView

interface RateDokterMvpView : MvpView {
    fun redirectToHome()
    fun loadDataDokter(data: DataDokterResponse)
}
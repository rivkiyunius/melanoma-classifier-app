package com.example.melanomaclassifier.ui.detail_periksa.view

import com.example.melanomaclassifier.data.network.response.DetailPeriksaResponse
import com.example.melanomaclassifier.ui.base.view.MvpView

interface DetailPeriksaMvpView: MvpView {
    fun showDetail(data: DetailPeriksaResponse)
}
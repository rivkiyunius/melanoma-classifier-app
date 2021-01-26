package com.example.melanomaclassifier.data.network.response

import com.google.gson.annotations.SerializedName

data class ListPeriksaResponse(@SerializedName("data") val periksa: List<PeriksaPasienModel>)
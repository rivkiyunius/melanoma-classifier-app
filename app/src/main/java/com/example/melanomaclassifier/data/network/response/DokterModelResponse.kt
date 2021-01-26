package com.example.melanomaclassifier.data.network.response

import com.google.gson.annotations.SerializedName

class DokterModelResponse(@SerializedName("data") val data: List<DokterModel>) {
}
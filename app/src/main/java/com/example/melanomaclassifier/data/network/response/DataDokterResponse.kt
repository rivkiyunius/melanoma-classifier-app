package com.example.melanomaclassifier.data.network.response


import com.google.gson.annotations.SerializedName

data class DataDokterResponse(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama")
    val nama: String
)
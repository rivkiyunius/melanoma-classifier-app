package com.example.melanomaclassifier.data.network.response


import com.google.gson.annotations.SerializedName

data class RatingDokterResponse(
    @SerializedName("create_at")
    val createAt: String,
    @SerializedName("dokter_id")
    val dokterId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modified_at")
    val modifiedAt: String,
    @SerializedName("note")
    val note: String,
    @SerializedName("rate")
    val rate: Int
)
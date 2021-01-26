package com.example.melanomaclassifier.data.network.request

import com.google.gson.annotations.SerializedName

class RatingDokterRequest: BaseRequest() {
    @SerializedName("rate")
    var rate : Int? = null
    @SerializedName("note")
    var note : String? = null
    @SerializedName("dokter_id")
    var dokterId: Int? = null
}
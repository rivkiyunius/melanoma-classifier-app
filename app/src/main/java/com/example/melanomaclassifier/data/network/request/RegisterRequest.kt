package com.example.melanomaclassifier.data.network.request

import com.google.gson.annotations.SerializedName

class RegisterRequest: BaseRequest() {
    var nama: String? = null
    var email: String? = null
    var password: String? = null
    @SerializedName("device_id")
    var deviceId: String? = null
}
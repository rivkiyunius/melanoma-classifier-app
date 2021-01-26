package com.example.melanomaclassifier.data.network.request

import com.google.gson.annotations.SerializedName

class LoginRequest : BaseRequest() {
    @SerializedName("email")
    var email: String? = null
    @SerializedName("password")
    var password: String? = null
    @SerializedName("device_id")
    var deviceId: String?  = null
}
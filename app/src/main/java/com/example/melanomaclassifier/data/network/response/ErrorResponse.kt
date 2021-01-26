package com.example.melanomaclassifier.data.network.response

import com.google.gson.GsonBuilder

class ErrorResponse(status: Boolean, message: String) {
    var status: Boolean? = status
    var message: String? = message

    companion object {
        fun fromRaw(string: String): ErrorResponse {
            val gson = GsonBuilder().create()
            try {
                return gson.fromJson<ErrorResponse>(string, ErrorResponse::class.java)
            } catch (e: Exception) {
                return ErrorResponse(false, e.localizedMessage)
            }
        }
    }
}
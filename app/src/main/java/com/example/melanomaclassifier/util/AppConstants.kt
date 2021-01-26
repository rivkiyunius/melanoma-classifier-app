package com.example.melanomaclassifier.util

object AppConstants {
    internal val PREF_NAME = "melanoma_pref"

    class ApiStatusCode{
        companion object{
            val FORBIDDEN = 403
            val INTERNAL_SERVER_ERROR = 500
            val NOT_FOUND = 404
            val UNAUTHORIZED = 401
            val BAD_REQUEST = 400
        }
    }
}
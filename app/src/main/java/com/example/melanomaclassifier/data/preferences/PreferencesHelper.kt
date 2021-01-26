package com.example.melanomaclassifier.data.preferences

interface PreferencesHelper {

    fun setToken(token: String)

    fun getToken(): String?

    fun resetAllPreferences()
}
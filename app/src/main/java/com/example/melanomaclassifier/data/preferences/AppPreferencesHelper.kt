package com.example.melanomaclassifier.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.melanomaclassifier.di.PreferenceInfo
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(
    context: Context,
    @PreferenceInfo private val prefFileName: String
) :
    PreferencesHelper {

    companion object {
        private const val KEY_USER_TOKEN = "KEY_USER_TOKEN"
    }

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun setToken(token: String) {
        mPrefs.edit { putString(KEY_USER_TOKEN, token) }
    }

    override fun getToken(): String? = mPrefs.getString(KEY_USER_TOKEN, null)

    override fun resetAllPreferences() {
        val editor = mPrefs.edit()
        editor.clear()
        editor.apply()
    }
}
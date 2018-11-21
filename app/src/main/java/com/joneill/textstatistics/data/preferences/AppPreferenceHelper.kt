package com.joneill.textstatistics.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.joneill.textstatistics.di.PreferenceInfo
import javax.inject.Inject

class AppPreferenceHelper @Inject constructor(context: Context,
                                              @PreferenceInfo private val prefFileName: String) : PreferenceHelper {
    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)


    override fun getAccessToken(): String = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, "")

    override fun setAccessToken(accessToken: String?) = mPrefs.edit { putString(PREF_KEY_ACCESS_TOKEN, accessToken) }

    companion object {
        var PREF_KEY_ACCESS_TOKEN : String = "access-token"
    }
}
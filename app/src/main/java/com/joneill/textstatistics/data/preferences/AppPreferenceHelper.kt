package com.joneill.textstatistics.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.joneill.textstatistics.di.PreferenceInfo
import javax.inject.Inject

class AppPreferenceHelper @Inject constructor(context: Context,
                                              @PreferenceInfo private val prefFileName: String) : PreferenceHelper {

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)


    companion object {
        var PREF_KEY_ACCESS_TOKEN : String = "access-token"
        var PREF_KEY_CURRENT_CONTACT : String = "current-contact"
    }

    override fun getAccessToken(): String = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, "")

    override fun setAccessToken(accessToken: String?) = mPrefs.edit { putString(PREF_KEY_ACCESS_TOKEN, accessToken) }

    override fun getCurrentContact(): String? = mPrefs.getString(PREF_KEY_CURRENT_CONTACT, "ABC")

    override fun setCurrentContact(contact: String?) = mPrefs.edit { putString(PREF_KEY_CURRENT_CONTACT, contact) }
}
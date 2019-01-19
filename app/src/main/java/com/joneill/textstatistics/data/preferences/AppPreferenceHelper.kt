package com.joneill.textstatistics.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import com.joneill.textstatistics.R
import com.joneill.textstatistics.di.PreferenceInfo
import javax.inject.Inject

class AppPreferenceHelper @Inject constructor(val context: Context,
                                              @PreferenceInfo private val prefFileName: String) : PreferenceHelper {

    private val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val prefKeyCurrentContact : String = context.getString(R.string.key_current_contact)
    private val prefKeyDarkModeEnabled : String = context.getString(R.string.key_dark_mode_enabled)

    override fun getCurrentContact(): String? = mPrefs.getString(prefKeyCurrentContact, "ABC")

    override fun setCurrentContact(contact: String?) = mPrefs.edit { putString(prefKeyCurrentContact, contact) }

    override fun getIsDarkTheme(): Boolean = mPrefs.getBoolean(prefKeyDarkModeEnabled, false)
}
package com.joneill.textstatistics.ui.settings.view

import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.FragmentTransaction
import com.joneill.textstatistics.R
import androidx.preference.PreferenceFragmentCompat




class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        activity?.recreate()
    }
}
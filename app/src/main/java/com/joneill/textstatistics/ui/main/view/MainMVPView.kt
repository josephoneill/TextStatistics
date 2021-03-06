package com.joneill.textstatistics.ui.main.view

import android.app.Activity
import com.joneill.textstatistics.ui.base.view.MVPView

interface MainMVPView : MVPView {
    fun setCustomTheme(isDarkTheme : Boolean)
    fun getViewActivity() : Activity
    fun loadHomeFragment()
    fun loadSettingsFragment()
}
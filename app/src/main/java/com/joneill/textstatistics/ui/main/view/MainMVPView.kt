package com.joneill.textstatistics.ui.main.view

import android.app.Activity
import android.content.Context
import com.joneill.textstatistics.data.text.Contact
import com.joneill.textstatistics.ui.base.view.MVPView

interface MainMVPView : MVPView {
    fun getViewActivity() : Activity
    fun loadHomeFragment()
}
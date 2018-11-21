package com.joneill.textstatistics.ui.main.interactor

import android.graphics.BitmapFactory
import com.joneill.textstatistics.data.preferences.PreferenceHelper
import com.joneill.textstatistics.ui.base.interactor.BaseInteractor
import javax.inject.Inject
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import com.joneill.textstatistics.data.text.Contact
import com.joneill.textstatistics.data.text.TextDataHelper


class MainInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, context : Context, private val textDataHelper : TextDataHelper) : BaseInteractor(preferenceHelper = preferenceHelper), MainMVPInteractor {

    /*override fun getUserDetails() = Pair(preferenceHelper.getCurrentUserName(),
            preferenceHelper.getCurrentUserEmail())*/
}
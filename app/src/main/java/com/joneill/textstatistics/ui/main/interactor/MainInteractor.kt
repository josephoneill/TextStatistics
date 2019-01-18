package com.joneill.textstatistics.ui.main.interactor

import com.joneill.textstatistics.data.preferences.PreferenceHelper
import com.joneill.textstatistics.ui.base.interactor.BaseInteractor
import javax.inject.Inject
import android.content.Context
import com.joneill.textstatistics.data.text.TextDataHelper


class MainInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, context : Context, private val textDataHelper : TextDataHelper) : BaseInteractor(preferenceHelper = preferenceHelper), MainMVPInteractor {

    /*override fun getUserDetails() = Pair(preferenceHelper.getCurrentUserName(),
            preferenceHelper.getCurrentUserEmail())*/
}
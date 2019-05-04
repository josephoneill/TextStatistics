package com.joneill.textstatistics.ui.main.interactor

import com.joneill.textstatistics.data.preferences.PreferenceHelper
import com.joneill.textstatistics.data.text.TextDataHelper
import com.joneill.textstatistics.ui.base.interactor.BaseInteractor
import javax.inject.Inject


class MainInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, private val textDataHelper : TextDataHelper) : BaseInteractor(preferenceHelper = preferenceHelper), MainMVPInteractor {

    /*override fun getUserDetails() = Pair(preferenceHelper.getCurrentUserName(),
            preferenceHelper.getCurrentUserEmail())*/

    override fun getIsDarkTheme(): Boolean = preferenceHelper.getIsDarkTheme()
}
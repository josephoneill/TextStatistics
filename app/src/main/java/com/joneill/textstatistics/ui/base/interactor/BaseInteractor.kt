package com.joneill.textstatistics.ui.base.interactor

import com.joneill.textstatistics.data.preferences.PreferenceHelper
import javax.inject.Inject

open class BaseInteractor() : MVPInteractor {

    protected lateinit var preferenceHelper: PreferenceHelper

    @Inject
    constructor(preferenceHelper: PreferenceHelper) : this() {
        this.preferenceHelper = preferenceHelper
    }
}
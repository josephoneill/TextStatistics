package com.joneill.textstatistics.ui.main.interactor

import com.joneill.textstatistics.ui.base.interactor.MVPInteractor

interface MainMVPInteractor : MVPInteractor {
    fun getIsDarkTheme() : Boolean
}
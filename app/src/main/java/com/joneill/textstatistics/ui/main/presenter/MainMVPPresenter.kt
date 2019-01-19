package com.joneill.textstatistics.ui.main.presenter

import com.joneill.textstatistics.ui.base.presenter.MVPPresenter
import com.joneill.textstatistics.ui.main.interactor.MainMVPInteractor
import com.joneill.textstatistics.ui.main.view.MainMVPView

interface MainMVPPresenter<V : MainMVPView, I : MainMVPInteractor> : MVPPresenter<V, I> {
    fun requestPermissions()
    fun onPermissionsResult()
    fun onSettingsMenuClicked()
    fun getCustomTheme()
}
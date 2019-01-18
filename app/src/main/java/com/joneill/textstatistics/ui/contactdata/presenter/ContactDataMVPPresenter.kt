package com.joneill.textstatistics.ui.main.presenter

import com.joneill.textstatistics.ui.base.presenter.MVPPresenter
import com.joneill.textstatistics.ui.home.view.ContactDataMVPView
import com.joneill.textstatistics.ui.main.interactor.ContactDataMVPInteractor

interface ContactDataMVPPresenter<V : ContactDataMVPView, I : ContactDataMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
}
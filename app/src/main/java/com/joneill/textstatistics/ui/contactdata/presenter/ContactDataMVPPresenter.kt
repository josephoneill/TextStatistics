package com.joneill.textstatistics.ui.contactdata.presenter

import com.joneill.textstatistics.ui.base.presenter.MVPPresenter
import com.joneill.textstatistics.ui.contactdata.interactor.ContactDataMVPInteractor
import com.joneill.textstatistics.ui.contactdata.view.ContactDataMVPView

interface ContactDataMVPPresenter<V : ContactDataMVPView, I : ContactDataMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
}
package com.joneill.textstatistics.ui.main.presenter

import com.joneill.textstatistics.data.text.Contact
import com.joneill.textstatistics.ui.base.presenter.MVPPresenter
import com.joneill.textstatistics.ui.home.view.ContactDataMVPView
import com.joneill.textstatistics.ui.home.view.HomeMVPView
import com.joneill.textstatistics.ui.main.interactor.ContactDataMVPInteractor
import com.joneill.textstatistics.ui.main.interactor.HomeMVPInteractor
import com.joneill.textstatistics.ui.main.interactor.MainMVPInteractor
import com.joneill.textstatistics.ui.main.view.MainMVPView

interface ContactDataMVPPresenter<V : ContactDataMVPView, I : ContactDataMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
}
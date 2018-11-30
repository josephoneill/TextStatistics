package com.joneill.textstatistics.ui.main.presenter

import com.joneill.textstatistics.ui.base.presenter.MVPPresenter
import com.joneill.textstatistics.ui.home.view.HomeMVPView
import com.joneill.textstatistics.ui.main.interactor.HomeMVPInteractor
import com.joneill.textstatistics.data.text.Contact


interface HomeMVPPresenter<V : HomeMVPView, I : HomeMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
    fun onContactItemClick(contact : Contact)
}
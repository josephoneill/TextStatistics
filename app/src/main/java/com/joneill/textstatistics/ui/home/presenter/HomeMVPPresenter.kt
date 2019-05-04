package com.joneill.textstatistics.ui.home.presenter

import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.presenter.MVPPresenter
import com.joneill.textstatistics.ui.home.interactor.HomeMVPInteractor
import com.joneill.textstatistics.ui.home.view.HomeMVPView


interface HomeMVPPresenter<V : HomeMVPView, I : HomeMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
    fun onContactItemClick(contact : Contact)
    fun onTabSelected(pos : Int)
}
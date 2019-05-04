package com.joneill.textstatistics.ui.home.presenter.adapter

import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.presenter.MVPPresenter
import com.joneill.textstatistics.ui.home.interactor.HomeMVPInteractor
import com.joneill.textstatistics.ui.home.view.adapter.TopContactsMVPView


interface TopContactsMVPPresenter<V : TopContactsMVPView, I : HomeMVPInteractor> : MVPPresenter<V, I> {
    fun setContactsList(contactsList : List<Pair<Contact?, Int>>)
    fun onBind(holder : TopContactsMVPView, position : Int)
    fun onContactClicked(position : Int)
    fun getItemCount() : Int
}
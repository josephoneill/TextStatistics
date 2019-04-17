package com.joneill.textstatistics.ui.home.presenter.adapter

import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.presenter.BasePresenter
import com.joneill.textstatistics.ui.home.view.adapter.TopContactsMVPView
import com.joneill.textstatistics.ui.main.interactor.HomeMVPInteractor
import javax.inject.Inject

open class TopContactsPresenter<V : TopContactsMVPView, I : HomeMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V, I>(interactor = interactor), TopContactsMVPPresenter<V, I> {
    private var contactsList : List<Pair<Contact?, Int>> = mutableListOf()

    override fun setContactsList(contactsList : List<Pair<Contact?, Int>>) {
        this.contactsList = contactsList
    }

    override fun onBind(holder: TopContactsMVPView, position: Int) {
        when {
            contactsList.isNotEmpty() -> holder.setContactName(contactsList[position].first?.name)
        }
    }

    override fun getItemCount() : Int = contactsList.size

}
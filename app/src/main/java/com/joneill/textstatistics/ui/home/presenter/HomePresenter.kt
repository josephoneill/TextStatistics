package com.joneill.textstatistics.ui.main.presenter

import com.joneill.textstatistics.data.text.Contact
import com.joneill.textstatistics.ui.base.presenter.BasePresenter
import com.joneill.textstatistics.ui.home.view.HomeMVPView
import com.joneill.textstatistics.ui.main.interactor.HomeMVPInteractor
import javax.inject.Inject
import com.google.gson.Gson


open class HomePresenter<V : HomeMVPView, I : HomeMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V, I>(interactor = interactor), HomeMVPPresenter<V, I> {

    override fun onViewPrepared() {
        interactor!!.loadData().subscribe {
            getConversations()
        }
    }

    private fun getConversations() = interactor?.let { it ->
        val contacts = it.getContacts()
        getView()?.displayContactsList(contacts)
    }

    override fun onContactItemClick(contact : Contact) {
        val gson = Gson()
        interactor!!.setCurrentContact(gson.toJson(contact))
        getView()?.openContactDataFragment(contact)
    }
}
package com.joneill.textstatistics.ui.main.presenter

import com.google.gson.Gson
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.presenter.BasePresenter
import com.joneill.textstatistics.ui.home.view.ContactDataMVPView
import com.joneill.textstatistics.ui.main.interactor.ContactDataMVPInteractor
import javax.inject.Inject



open class ContactDataPresenter<V : ContactDataMVPView, I : ContactDataMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V, I>(interactor = interactor), ContactDataMVPPresenter<V, I> {

    private var contact : Contact? = null

    override fun onViewPrepared() {
        val gson = Gson()
        this.contact = gson.fromJson(interactor!!.getCurrentContact(), Contact::class.java)
        getMessages(contact!!)
    }

    private fun getMessages(contact : Contact) = interactor?.let { it ->
        var messages = it.getMessages()
        messages = it.getMessagesByContact(messages, contact)
        //messages = it.getMessagesInDateRange(messages, Date(System.currentTimeMillis() - 7L * 24 * 3600 * 1000), Date(System.currentTimeMillis()))
        getView()?.displayMessagesList(messages)
    }
}
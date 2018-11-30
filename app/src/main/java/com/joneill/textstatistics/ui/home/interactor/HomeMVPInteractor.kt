package com.joneill.textstatistics.ui.main.interactor

import com.joneill.textstatistics.data.text.Contact
import com.joneill.textstatistics.data.text.Message
import com.joneill.textstatistics.ui.base.interactor.MVPInteractor
import io.reactivex.Completable

interface HomeMVPInteractor : MVPInteractor {
    fun loadData() : Completable
    fun getContacts() : List<Contact>
    fun getConversations(contacts : List<Contact>) : List<Message>
    fun getMessagesByContact(messages : List<Message>, contact : Contact) : List<Message>
    fun setCurrentContact(contact : String?)
}
package com.joneill.textstatistics.ui.main.interactor

import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.interactor.MVPInteractor
import io.reactivex.Completable
import java.util.*

interface HomeMVPInteractor : MVPInteractor {
    fun loadData() : Completable
    fun getContacts() : List<Contact>
    fun getMessages() : List<Message>
    fun getMessagesByContact(messages : List<Message>, contact : Contact) : List<Message>
    fun getMessageCountByDate(messages : List<Message>) : Map<String, Int>
    fun getMessagesInDateRange(messages : List<Message>, startDate : Date, endDate : Date) : List<Message>
    fun setCurrentContact(contact : String?)
    fun getContactsSortedByMessageCount(messages : List<Message>) : List<Pair<Contact?, Int>>
}
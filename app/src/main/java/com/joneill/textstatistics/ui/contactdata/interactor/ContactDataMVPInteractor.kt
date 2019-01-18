package com.joneill.textstatistics.ui.main.interactor

import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.interactor.MVPInteractor
import java.util.*

interface ContactDataMVPInteractor : MVPInteractor {
    fun getMessages() : List<Message>
    fun getMessagesByContact(messages : List<Message>, contact : Contact) : List<Message>
    fun getMessagesByDate(messages : List<Message>, date : Date) : List<Message>
    fun getMessageCountOnDate(messages : List<Message>, date : Date) : Int
    fun getMessagesInDateRange(messages : List<Message>, startDate : Date, endDate : Date) : List<Message>
    fun getCurrentContact() : String?
}
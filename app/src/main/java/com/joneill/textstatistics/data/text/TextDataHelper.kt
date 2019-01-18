package com.joneill.textstatistics.data.text

import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.data.text.data.Message
import java.util.*

interface TextDataHelper {
    fun getAllConversations(contacts : List<Contact>): List<Message>
    fun getContacts() : List<Contact>
    fun filterCreatorsContactsOnly(contacts : List<Contact>?) : List<Contact>
    fun getContactByNumber(contacts : List<Contact>, number : String?) : Contact?
    fun getMessagesByContact(messages : List<Message>, contact : Contact?) : List<Message>
    fun getMessagesByDate(messages : List<Message>, date : Date) : List<Message>
    fun getMessageCountOnDate(messages : List<Message>, date : Date) : Int
    fun getMessageCountByDate(messages : List<Message>) : Map<String, Int>
    fun getMessagesInDateRange(messages : List<Message>, startDate : Date, endDate : Date) : List<Message>
}
package com.joneill.textstatistics.ui.contactdata.interactor

import com.joneill.textstatistics.data.preferences.PreferenceHelper
import com.joneill.textstatistics.data.repository.MessagesRepository
import com.joneill.textstatistics.data.text.AppTextDataHelper
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.interactor.BaseInteractor
import java.util.*
import javax.inject.Inject


class ContactDataInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, private val textDataHelper: AppTextDataHelper, private val messagesRepository: MessagesRepository) : BaseInteractor(preferenceHelper = preferenceHelper), ContactDataMVPInteractor {

    override fun getMessages(): List<Message> = messagesRepository.messages

    override fun getMessagesByDate(messages : List<Message>, date: Date): List<Message> = messagesRepository.getMessagesByDate(messages, date)

    override fun getMessagesByContact(messages : List<Message>, contact: Contact): List<Message> = textDataHelper.getMessagesByContact(messages, contact)

    override fun getCurrentContact(): String? = preferenceHelper.getCurrentContact()

    override fun getMessagesInDateRange(messages: List<Message>, startDate: Date, endDate: Date): List<Message> = textDataHelper.getMessagesInDateRange(messages, startDate, endDate)

    override fun getMessageCountByDate(messages : List<Message>) : Map<String, Int> = textDataHelper.getMessageCountByDate(messages)

    override fun getMessageCountOnDate(messages: List<Message>, date: Date): Int = textDataHelper.getMessageCountOnDate(messages, date)

    /*override fun filterCreatorsContactsOnly(contacts : List<Contact>?): List<Contact>? {
        return textDataHelper.filterCreatorsContactsOnly(contacts)
    }*/
}
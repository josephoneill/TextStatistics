package com.joneill.textstatistics.ui.home.interactor

import com.joneill.textstatistics.data.preferences.PreferenceHelper
import com.joneill.textstatistics.data.repository.MessagesRepository
import com.joneill.textstatistics.data.text.AppTextDataHelper
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.interactor.BaseInteractor
import java.util.*
import javax.inject.Inject


class HomeInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, private val textDataHelper: AppTextDataHelper, private val messagesRepository: MessagesRepository) : BaseInteractor(preferenceHelper = preferenceHelper), HomeMVPInteractor {
    override fun loadData() = messagesRepository.loadData()

    override fun getContacts(): List<Contact> = messagesRepository.contacts

    override fun getMessages(): List<Message> = messagesRepository.messages

    override fun getMessagesByContact(messages: List<Message>, contact: Contact): List<Message> = messagesRepository.getMessagesByContact(contact)

    override fun setCurrentContact(contact : String?) = preferenceHelper.setCurrentContact(contact)

    override fun getMessagesInDateRange(messages: List<Message>, startDate: Date, endDate: Date): List<Message> = textDataHelper.getMessagesInDateRange(messages, startDate, endDate)

    override fun getMessageCountByDate(messages : List<Message>) : Map<String, Int> = textDataHelper.getMessageCountByDate(messages)

    override fun getContactsSortedByMessageCount(messages : List<Message>) : List<Pair<Contact?, Int>> = textDataHelper.getContactsSortedByMessageCount(messages)

    /*override fun filterCreatorsContactsOnly(contacts : List<Contact>?): List<Contact>? {
        return textDataHelper.filterCreatorsContactsOnly(contacts)
    }*/
}
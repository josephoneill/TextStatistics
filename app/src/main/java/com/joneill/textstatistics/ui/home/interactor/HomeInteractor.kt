package com.joneill.textstatistics.ui.main.interactor

import com.joneill.textstatistics.data.preferences.PreferenceHelper
import com.joneill.textstatistics.ui.base.interactor.BaseInteractor
import javax.inject.Inject
import android.content.Context
import com.joneill.textstatistics.data.repository.MessagesRepository
import com.joneill.textstatistics.data.text.AppTextDataHelper
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.data.text.data.Message
import java.util.*


class HomeInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, context : Context, private val textDataHelper: AppTextDataHelper, private val messagesRepository: MessagesRepository) : BaseInteractor(preferenceHelper = preferenceHelper), HomeMVPInteractor {
    override fun loadData() = messagesRepository.loadData()

    override fun getContacts(): List<Contact> = messagesRepository.contacts

    override fun getMessages(): List<Message> = messagesRepository.messages

    override fun getMessagesByContact(messages: List<Message>, contact: Contact): List<Message> = messagesRepository.getMessagesByContact(contact)

    override fun setCurrentContact(contact : String?) = preferenceHelper.setCurrentContact(contact)

    override fun getMessagesInDateRange(messages: List<Message>, startDate: Date, endDate: Date): List<Message> = textDataHelper.getMessagesInDateRange(messages, startDate, endDate)

    override fun getMessageCountByDate(messages : List<Message>) : Map<String, Int> = textDataHelper.getMessageCountByDate(messages)

    /*override fun filterCreatorsContactsOnly(contacts : List<Contact>?): List<Contact>? {
        return textDataHelper.filterCreatorsContactsOnly(contacts)
    }*/
}
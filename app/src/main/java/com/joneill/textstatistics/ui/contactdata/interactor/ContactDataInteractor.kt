package com.joneill.textstatistics.ui.main.interactor

import com.joneill.textstatistics.data.preferences.PreferenceHelper
import com.joneill.textstatistics.ui.base.interactor.BaseInteractor
import javax.inject.Inject
import android.content.Context
import com.joneill.textstatistics.data.repository.MessagesRepository
import com.joneill.textstatistics.data.text.Contact
import com.joneill.textstatistics.data.text.Message
import com.joneill.textstatistics.data.text.TextDataHelper


class ContactDataInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, context : Context, private val messagesRepository: MessagesRepository) : BaseInteractor(preferenceHelper = preferenceHelper), ContactDataMVPInteractor {

    override fun getMessagesByContact(contact: Contact): List<Message> = messagesRepository.getMessagesByContact(contact)

    override fun getCurrentContact(): String? = preferenceHelper.getCurrentContact()
    /*override fun filterCreatorsContactsOnly(contacts : List<Contact>?): List<Contact>? {
        return textDataHelper.filterCreatorsContactsOnly(contacts)
    }*/
}
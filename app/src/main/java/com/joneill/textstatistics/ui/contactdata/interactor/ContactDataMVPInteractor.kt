package com.joneill.textstatistics.ui.main.interactor

import com.joneill.textstatistics.data.text.Contact
import com.joneill.textstatistics.data.text.Message
import com.joneill.textstatistics.ui.base.interactor.MVPInteractor

interface ContactDataMVPInteractor : MVPInteractor {
    fun getMessagesByContact(contact : Contact) : List<Message>
    fun getCurrentContact() : String?
}
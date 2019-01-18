package com.joneill.textstatistics.data.repository

import com.joneill.textstatistics.data.text.AppTextDataHelper
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.data.text.data.Message
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MessagesRepository @Inject
constructor(private val textDataHelper: AppTextDataHelper) : MessagesRepo {

    var contacts : List<Contact> = mutableListOf()
    var messages : List<Message> = mutableListOf()

    override fun loadData(): Completable =
        Completable.fromAction {
            if(messages.isEmpty()) {
                contacts = textDataHelper.getContacts()
                messages = textDataHelper.getAllConversations(contacts)
            }
        }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

    fun getMessagesByContact(contact : Contact) : List<Message> {
        return textDataHelper.getMessagesByContact(messages, contact)
    }

    fun getMessagesByDate(messages : List<Message>, date : Date) : List<Message> = textDataHelper.getMessagesByDate(messages, date)
}
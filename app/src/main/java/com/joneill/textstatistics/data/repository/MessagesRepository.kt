package com.joneill.textstatistics.data.repository

import com.joneill.textstatistics.data.text.AppTextDataHelper
import com.joneill.textstatistics.data.text.Contact
import com.joneill.textstatistics.data.text.Message
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
}
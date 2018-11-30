package com.joneill.textstatistics.data.text

interface TextDataHelper {
    fun getAllConversations(contacts : List<Contact>): List<Message>
    fun getContacts() : List<Contact>
    fun filterCreatorsContactsOnly(contacts : List<Contact>?) : List<Contact>
    fun getContactByNumber(contacts : List<Contact>, number : String?) : Contact?
    fun getMessagesByContact(messages : List<Message>, contact : Contact?) : List<Message>
}
package com.joneill.textstatistics.data.text

interface TextDataHelper {
    fun getAllConversations(): List<String>?
    fun getAllCreators() : List<Contact>?
    fun getContacts() : List<Contact>?
    fun filterCreatorsContactsOnly(contacts : List<Contact>?) : List<Contact>?
}
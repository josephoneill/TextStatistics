package com.joneill.textstatistics.data.preferences


interface PreferenceHelper {
    fun getCurrentContact() : String?
    fun setCurrentContact(contact : String?)
    fun getIsDarkTheme() : Boolean
}
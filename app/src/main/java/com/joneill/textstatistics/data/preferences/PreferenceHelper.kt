package com.joneill.textstatistics.data.preferences


/**
 * Created by jyotidubey on 04/01/18.
 */
interface PreferenceHelper {

    fun getAccessToken(): String?
    fun setAccessToken(accessToken: String?)
    fun getCurrentContact() : String?
    fun setCurrentContact(contact : String?)
}
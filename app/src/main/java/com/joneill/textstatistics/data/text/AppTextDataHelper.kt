package com.joneill.textstatistics.data.text

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import android.provider.Telephony
import android.util.Log
import com.joneill.textstatistics.util.CommonUtil
import javax.inject.Inject

class AppTextDataHelper @Inject constructor(private val context: Context) : TextDataHelper {

    @SuppressLint("InlinedApi")
    private val CONTACT_PROJECTION: Array<out String> = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.HAS_PHONE_NUMBER,
            ContactsContract.Contacts.LOOKUP_KEY,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
            else
                ContactsContract.Contacts.DISPLAY_NAME
    )

    // Defines the text expression
    @SuppressLint("InlinedApi")
    private val SELECTION: String =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} LIKE ?"
            else
                "${ContactsContract.Contacts.DISPLAY_NAME} LIKE ?"

    // Defines a variable for the search string
    private val mSearchString: String = ""

    private val contentResolver = context.contentResolver!!

    private val NUMBER_REGEX = Regex("\\D+")

    /**
     * Get a list of all sms conversations
     * @param contacts the list of contacts to match the sender to
     * @return a {@List<Text>} of all sms messages
     */
    override fun getAllConversations(contacts : List<Contact>): List<Message> {
        val conversations = arrayListOf<Message>()
        val projection = arrayOf(Telephony.Sms.Conversations._ID, Telephony.Sms.Conversations.BODY, Telephony.Sms.Conversations.ADDRESS)
        val uri = Uri.parse("content://mms-sms/complete-conversations")
        val cursor = contentResolver.query(uri, projection, null, null, null)
        try {
            while (cursor!!.moveToNext()) {
                val body = cursor.getString(cursor.getColumnIndex(Telephony.Sms.Conversations.BODY))
                var number = cursor.getString(cursor.getColumnIndex(Telephony.Sms.Conversations.ADDRESS))
                number = number?.replace(NUMBER_REGEX, "")
                val contact = getContactByNumber(contacts, number)
                val message = Message(body, contact)
                conversations.add(message)
            }
        } finally {
            cursor!!.close()
        }
        return conversations
    }

    /**
     * Filters a list of Contacts numbers to only those with names assigned
     * @param contacts the list of contacts to be filtered
     * @return a {@List<Contact>?)} with non-contacts removed
     */
    override fun filterCreatorsContactsOnly(contacts: List<Contact>?): List<Contact> {
        return contacts!!.filter { !it.name.isEmpty() }
    }

    override fun getContacts(): List<Contact> {
        val contacts: MutableList<Contact> = mutableListOf()
        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, CONTACT_PROJECTION, null, null,
                null)

        if (cursor!!.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val image = CommonUtil.getContactImage(context, id)
                var number = ""

                val hasNumber = cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)).toInt()

                if (hasNumber > 0) {
                    val cursorPhone = this.contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id), null)

                    if (cursorPhone!!.count > 0) {
                        while (cursorPhone.moveToNext()) {
                            number = cursorPhone.getString(
                                    cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            number = number.replace(NUMBER_REGEX, "")
                        }
                    }
                    cursorPhone.close()
                }

                contacts.add(Contact(name, number, image))
            }
        }
        cursor.close()
        return contacts
    }

    /**
     * Finds the {@Contact} object with the associated number
     * @param contacts the list of contacts to be filtered
     * @param number the number of the {@Contact} to find
     * @return the {@Contact} object that contains the associated number
     */
    override fun getContactByNumber(contacts: List<Contact>, number: String?): Contact? = contacts.find {it.number == number}

    /**
     * Gets a list of all {@Message} objects linked to the {@Contact} param
     * @param messages the list of messages to be filtered
     * @param contact the {@Contact} to filter by
     * @return a {@List<Message>} object that contains the messages linked to the {@Contact}
     */
    override fun getMessagesByContact(messages: List<Message>, contact: Contact?) = messages.filter {it.contact?.number == contact?.number}
}
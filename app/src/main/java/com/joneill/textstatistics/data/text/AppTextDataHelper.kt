package com.joneill.textstatistics.data.text

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import android.provider.Telephony
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.util.CommonUtil
import java.text.SimpleDateFormat
import java.util.*
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

    private val contentResolver = context.contentResolver!!

    private val NUMBER_REGEX = Regex("\\D+")

    /**
     * Get a list of all sms conversations
     * @param contacts the list of contacts to match the sender to
     * @return a {@code List<Text>} of all sms messages
     */
    override fun getAllConversations(contacts: List<Contact>): List<Message> {
        val conversations = arrayListOf<Message>()
        val projection = arrayOf(Telephony.Sms.Conversations._ID, Telephony.Sms.Conversations.BODY, Telephony.Sms.Conversations.ADDRESS,
                Telephony.Sms.Conversations.DATE)
        val uri = Uri.parse("content://mms-sms/complete-conversations")
        val cursor = contentResolver.query(uri, projection, null, null, null)
        try {
            while (cursor!!.moveToNext()) {
                val body = cursor.getString(cursor.getColumnIndex(Telephony.Sms.Conversations.BODY))
                var number = cursor.getString(cursor.getColumnIndex(Telephony.Sms.Conversations.ADDRESS))
                number = number?.replace(NUMBER_REGEX, "")
                val contact = getContactByNumber(contacts, number)
                val date = cursor.getString(cursor.getColumnIndex(Telephony.Sms.Conversations.DATE)).toLong()
                val message = Message(body, contact, Date(date))

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
     * @return a {@code List<Contact>?)} with non-contacts removed
     */
    override fun filterCreatorsContactsOnly(contacts: List<Contact>?): List<Contact> {
        return contacts!!.filter { !it.name.isEmpty() }
    }

    /**
     * Gets a list of the Contacts saved on the phone
     * @return a {@code List<Contact>} of all of the Contacts on the phone
     */
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
     * Finds the {@link Contact} object with the associated number
     * @param contacts the list of contacts to be filtered
     * @param number the number of the {@link Contact} to find
     * @return the {@link Contact} object that contains the associated number
     */
    override fun getContactByNumber(contacts: List<Contact>, number: String?): Contact? = contacts.find { it.number == number }

    /**
     * Gets a list of all {@link Message} objects linked to the {@Contact} param
     * @param messages the list of messages to be filtered
     * @param contact the {@Contact} to filter by
     * @return a {@List<Message>} object that contains the messages linked to the {@Contact}
     */
    override fun getMessagesByContact(messages: List<Message>, contact: Contact?) = messages.filter { it.contact?.number == contact?.number }

    /**
     * Gets a list of {@link Message} objects that correspond to a given date
     * @param messages the list of messages to be searched
     * @param date the Date to filter by
     * @return a {@code List<Message>} object that contains the messages corresponding to the given date
     */
    override fun getMessagesByDate(messages: List<Message>, date: Date): List<Message> = messages.filter { CommonUtil.getFormattedDate(it.date) == CommonUtil.getFormattedDate(date) }

    /**
     * Gets the count of {@link Message} objects that correspond to a given date
     * @param messages the list of messages to be searched
     * @param date the Date to filter by
     * @return an Int value of the number of messages on the given date
     */
    override fun getMessageCountOnDate(messages: List<Message>, date: Date): Int = messages.count { CommonUtil.getFormattedDate(it.date) == CommonUtil.getFormattedDate(date) }

    /**
     * Gets the count of {@link Message} objects for each date in the messages list
     * @param messages the list of messages to be searched
     * @return a {@code Map<String, Int>} where the key is the Date of the message
     *         and the value is the count
     */
    override fun getMessageCountByDate(messages: List<Message>): Map<String, Int> {
        var map : MutableMap<String, Int> = mutableMapOf()

        for(m : Message in messages) {
            val date = SimpleDateFormat("MM/dd").format(m.date)
            if(map.containsKey(date)) {
                map[date] = map[date]!! + 1
            } else {
                map[date] = 1
            }
        }

        return map
    }

    /**
     * Gets a list of {@link Message} objects that fall within a given date range
     * @param messages the list of messages to be searched
     * @param startDate the start Date to filter by
     * @param endDate the end Date to filter by
     * @return a {@code List<Message>} object that contains the messages within the given date range
     */
    override fun getMessagesInDateRange(messages: List<Message>, startDate: Date, endDate: Date): List<Message> = messages.filter { !(it.date.before(startDate) || it.date.after(endDate)) }

    override fun getContactsSortedByMessageCount(messages: List<Message>): List<Pair<Contact?, Int>> {
        var map : MutableMap<Contact?, Int> = mutableMapOf()
        var list: List<Pair<Contact?, Int>>
        for (m : Message in messages) {
            if(map.containsKey(m.contact)) {
                map[m.contact] = map[m.contact]!!+1
            } else {
                map[m.contact] = 1
            }
        }

        list = map.toList()
        list.sortedWith(compareBy {it.second})
        return list
    }
}
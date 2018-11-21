package com.joneill.textstatistics.data.text

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import android.provider.Telephony
import android.util.Log
import androidx.cursoradapter.widget.SimpleCursorAdapter
import com.joneill.textstatistics.util.CommonUtil
import javax.inject.Inject


// The column index for the _ID column
private const val CONTACT_ID_INDEX: Int = 0
// The column index for the CONTACT_KEY column
private const val CONTACT_KEY_INDEX: Int = 1

class AppTextDataHelper @Inject constructor(private val context: Context) : TextDataHelper {

    /*
 * Defines an array that contains column names to move from
 * the Cursor to the ListView.
 */
    @SuppressLint("InlinedApi")
    private val FROM_COLUMNS: Array<String> = arrayOf(
            if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)) {
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
            } else {
                ContactsContract.Contacts.DISPLAY_NAME
            }
    )
    /*
     * Defines an array that contains resource ids for the layout views
     * that get the Cursor column contents. The id is pre-defined in
     * the Android framework, so it is prefaced with "android.R.id"
     */
    private val TO_IDS: IntArray = intArrayOf(android.R.id.text1)
    // Define variables for the contact the user selects
    // The contact's _ID value
    var mContactId: Long = 0
    // The contact's LOOKUP_KEY
    var mContactKey: String? = null
    // A content URI for the selected contact
    var mContactUri: Uri? = null

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
    // Defines the array to hold values that replace the ?
    private val mSelectionArgs = arrayOf<String>(mSearchString)

    private val contentResolver = context.contentResolver!!

    /**
     * Get a list of all sms conversations
     * @return a {@List<String>} of all sms messages
     */
    override fun getAllConversations(): List<String> {
        val conversations = arrayListOf<String>()
        val projection = arrayOf(Telephony.Sms.Conversations._ID, Telephony.Sms.Conversations.BODY)
        val uri = Uri.parse("content://mms-sms/complete-conversations")
        val cursor = contentResolver.query(uri, projection, null, null, null)
        try {
            while (cursor!!.moveToNext()) {
                val body = cursor.getString(cursor.getColumnIndex("body"))
                conversations.add(body)
            }
        } finally {
            cursor!!.close()
        }
        return conversations
    }

    /**
     * Get a list of all "creators", or the names of those who have sent a message
     * @return a {@List<Contact>?} of all the creators
     */
    override fun getAllCreators(): List<Contact>? {
        val creators: MutableList<Contact> = mutableListOf()
        val creatorsList = arrayListOf<String>()
        //val projection = arrayOf("Distinct " + Telephony.Sms.ADDRESS)
        val projection = arrayOf("Distinct " + Telephony.Sms.ADDRESS)
        val uri = Uri.parse("content://sms/inbox")
        val cursor = contentResolver.query(uri, projection, null, null, null)
        try {
            while (cursor!!.moveToNext()) {
                val creator = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS))
                creatorsList.add(creator)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        } finally {
            cursor!!.close()
        }

        for (number: String in creatorsList) {
            val displayName = CommonUtil.getContactName(context, number)
            //creators.add(Contact(displayName, number))
        }

        return creators
    }

    /**
     * Filters a list of Contacts numbers to only those with names assigned
     * @param contacts the list of contacts to be filtered
     * @return a {@List<Contact>?)} with non-contacts removed
     */
    override fun filterCreatorsContactsOnly(contacts: List<Contact>?): List<Contact>? {
        return contacts!!.filter { !it.name.isEmpty() }
    }

    override fun getContacts(): List<Contact>? {
        val contacts: MutableList<Contact> = mutableListOf()
        val cursor = contentResolver!!.query(ContactsContract.Contacts.CONTENT_URI, CONTACT_PROJECTION, null, null,
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
                    val cursorPhone = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id), null)

                    if (cursorPhone!!.count > 0) {
                        while (cursorPhone.moveToNext()) {
                            number = cursorPhone.getString(
                                    cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
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

}
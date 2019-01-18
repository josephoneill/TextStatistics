package com.joneill.textstatistics.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.ContactsContract
import android.graphics.BitmapFactory
import android.content.ContentUris
import java.io.ByteArrayInputStream
import java.text.SimpleDateFormat
import java.util.*


object CommonUtil {
    fun getContactName(context: Context?, number : String) : String {
        var displayName = ""
        val lookupUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number))
        val cursor = context!!.contentResolver.query(lookupUri, arrayOf(ContactsContract.Data.DISPLAY_NAME), null, null, null)
        try {
            while (cursor!!.moveToNext()) {
                displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor!!.close()
        }

        return displayName
    }

    fun getContactImage(context: Context?, id : String) : Bitmap? {
        val contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id.toLong())
        val photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY)
        val cursor = context!!.contentResolver.query(photoUri,
                arrayOf(ContactsContract.Contacts.Photo.PHOTO), null, null, null) ?: return null
        try {
            if (cursor.moveToFirst()) {
                val data = cursor.getBlob(0)
                if (data != null) {
                    return BitmapFactory.decodeStream(ByteArrayInputStream(data))
                }
            }
        } finally {
            cursor.close()
        }
        return null
    }

    fun getFormattedDate(date : Date) : String {
        val fmt = SimpleDateFormat("yyyyMMdd")
        return fmt.format(date)
    }

    fun getDateXDaysAgo(x : Long) : Date = Date(System.currentTimeMillis() - x * 24 * 3600 * 1000)
}

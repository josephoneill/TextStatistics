package com.joneill.textstatistics.data.text.data

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact constructor(val name: String, val number: String, val profileImage : Bitmap?) : Parcelable
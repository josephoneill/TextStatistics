package com.joneill.textstatistics.ui.home.view.adapter

import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.view.MVPView

interface TopContactsMVPView : MVPView {
    fun setOnClickListener()
    fun setContactName(name : String?)
    fun setContactMessageCount(count : Int)
    fun onClick(contact: Contact)
}
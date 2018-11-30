package com.joneill.textstatistics.ui.home.view

import com.joneill.textstatistics.data.text.Contact
import com.joneill.textstatistics.ui.base.view.MVPView

interface HomeMVPView : MVPView {
    fun displayContactsList(contacts: List<Contact>?) : Unit?
    fun openContactDataFragment(contact : Contact)
}
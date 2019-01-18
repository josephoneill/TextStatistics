package com.joneill.textstatistics.ui.home.view

import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.view.MVPView

interface ContactDataMVPView : MVPView {
    fun displayMessagesList(messages: List<Message>?) : Unit?
}
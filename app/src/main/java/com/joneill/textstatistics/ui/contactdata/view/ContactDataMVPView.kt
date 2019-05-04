package com.joneill.textstatistics.ui.contactdata.view

import com.github.mikephil.charting.data.Entry
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.view.MVPView

interface ContactDataMVPView : MVPView {
    fun displayMessagesList(messages: List<Message>?) : Unit?
    fun showChartCard(title : String, dataValue : String, dataList: List<Pair<String, Entry>>, animateX : Boolean)
}
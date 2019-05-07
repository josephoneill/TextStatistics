package com.joneill.textstatistics.ui.contactdata.view

import com.joneill.textstatistics.data.graph.ComparisonEntrySet
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.view.MVPView

interface ContactDataMVPView : MVPView {
    fun displayMessagesList(messages: List<Message>?) : Unit?
    fun showChartCard(title : String, dataValue : String, comparisonsList: List<ComparisonEntrySet>, animateX : Boolean)
}
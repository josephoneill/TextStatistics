package com.joneill.textstatistics.ui.home.view

import com.joneill.textstatistics.data.graph.ComparisonEntrySet
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.view.MVPView

interface HomeMVPView : MVPView {
    fun displayTopContactsList(list : List<Pair<Contact?, Int>>)
    fun showDashboard()
    fun showChartCard(title : String, dataValue : String, comparisonsList: List<ComparisonEntrySet>, animateX : Boolean)
    fun showTotalMessagesCard(total : Int)
    fun openContactDataFragment(contact : Contact)
}
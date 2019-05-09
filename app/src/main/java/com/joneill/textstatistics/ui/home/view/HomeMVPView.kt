package com.joneill.textstatistics.ui.home.view

import com.github.mikephil.charting.data.LineDataSet
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.view.MVPView

interface HomeMVPView : MVPView {
    fun displayTopContactsList(list : List<Pair<Contact?, Int>>)
    fun showDashboard()
    fun showChartCard(title : String, dataValue : String, dataSets: List<LineDataSet>, xAxisLabels : List<String>, animateX : Boolean)
    fun showTotalMessagesCard(total : Int)
    fun openContactDataFragment(contact : Contact)
    fun openStatsChartFragment()
}
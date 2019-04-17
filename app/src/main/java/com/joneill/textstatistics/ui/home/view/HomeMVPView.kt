package com.joneill.textstatistics.ui.home.view

import com.github.mikephil.charting.data.Entry
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.view.MVPView

interface HomeMVPView : MVPView {
    fun displayContactsList(contacts: List<Contact>?) : Unit?
    fun displayTopContactsList(list : List<Pair<Contact?, Int>>)
    fun showDashboard()
    fun showChartCard(title : String, dataValue : String, dataList: List<Pair<String, Entry>>, animateX : Boolean)
    fun openContactDataFragment(contact : Contact)
}
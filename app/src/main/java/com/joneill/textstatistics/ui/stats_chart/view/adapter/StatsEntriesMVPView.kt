package com.joneill.textstatistics.ui.stats_chart.view.adapter

import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.view.MVPView

interface StatsEntriesMVPView : MVPView {
    fun setOnClickListener()
    fun onClick(contact: Contact)
}
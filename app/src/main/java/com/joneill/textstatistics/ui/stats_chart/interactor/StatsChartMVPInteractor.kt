package com.joneill.textstatistics.ui.stats_chart.interactor

import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.interactor.MVPInteractor

interface StatsChartMVPInteractor : MVPInteractor {
    fun getMessages() : List<Message>
}
package com.joneill.textstatistics.ui.stats_chart.interactor

import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.interactor.MVPInteractor
import java.util.*

interface StatsChartMVPInteractor : MVPInteractor {
    fun getMessages() : List<Message>
    fun getMessagesByContact(messages : List<Message>, contact : Contact) : List<Message>
    fun getMessageCountOnDate(messages : List<Message>, date : Date) : Int
    fun getMessageCountByDate(messages : List<Message>) : Map<String, Int>
    fun getMessagesInDateRange(messages : List<Message>, startDate : Date, endDate : Date) : List<Message>
}
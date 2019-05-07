package com.joneill.textstatistics.ui.contactdata.presenter

import com.github.mikephil.charting.data.Entry
import com.google.gson.Gson
import com.joneill.textstatistics.data.graph.ComparisonEntrySet
import com.joneill.textstatistics.data.graph.CountedDateMessageEntry
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.presenter.BasePresenter
import com.joneill.textstatistics.ui.contactdata.interactor.ContactDataMVPInteractor
import com.joneill.textstatistics.ui.contactdata.view.ContactDataMVPView
import com.joneill.textstatistics.util.CommonUtil
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject



open class ContactDataPresenter<V : ContactDataMVPView, I : ContactDataMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V, I>(interactor = interactor), ContactDataMVPPresenter<V, I> {

    private var contact : Contact? = null

    override fun onViewPrepared() {
        val gson = Gson()
        this.contact = gson.fromJson(interactor!!.getCurrentContact(), Contact::class.java)
        getMessages(contact!!)
    }

    private fun getMessages(contact : Contact) = interactor?.let { it ->
        var messages = it.getMessages()
        messages = it.getMessagesByContact(messages, contact)
        //messages = it.getMessagesInDateRange(messages, Date(System.currentTimeMillis() - 7L * 24 * 3600 * 1000), Date(System.currentTimeMillis()))
        getView()?.displayMessagesList(messages)
        loadChart("", 30, true, messages)
    }

    private fun loadChart(title: String, days: Long, animateX: Boolean, data : List<Message>) = interactor?.let {
        var messages = data
        val comparisonsList : MutableList<ComparisonEntrySet> = mutableListOf()
        val sentEntries: MutableList<CountedDateMessageEntry> = mutableListOf()
        val receivedEntries: MutableList<CountedDateMessageEntry> = mutableListOf()
        messages = it.getMessagesInDateRange(messages, CommonUtil.getDateXDaysAgo(days), Date(System.currentTimeMillis()))
        val sentMessages = messages.filter { message -> message.sent}
        val receivedMessages = messages.filter { message -> !message.sent}
        val sentCounts = it.getMessageCountByDate(sentMessages)
        val receivedCounts = it.getMessageCountByDate(receivedMessages)

        for (i in 0 until days) {
            val date = CommonUtil.getDateXDaysAgo(days - 1 - i)
            val name = SimpleDateFormat("MM/dd").format(date)
            var sentCount: Float? = sentCounts[name]?.toFloat()
            var receivedCount: Float? = receivedCounts[name]?.toFloat()
            if(sentCount == null) sentCount = 0.0f
            if(receivedCount == null) receivedCount = 0.0f

            sentEntries.add(CountedDateMessageEntry(name, Entry(i.toFloat(), sentCount)))
            receivedEntries.add(CountedDateMessageEntry(name, Entry(i.toFloat(), receivedCount)))
        }

        comparisonsList.add(ComparisonEntrySet(sentEntries, ""))
        comparisonsList.add(ComparisonEntrySet(receivedEntries, ""))
        getView()?.showChartCard(title, messages.count().toString(), comparisonsList, animateX)
    }
}
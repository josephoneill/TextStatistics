package com.joneill.textstatistics.ui.contactdata.presenter

import com.github.mikephil.charting.data.Entry
import com.google.gson.Gson
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
        val entries: MutableList<Pair<String, Entry>> = mutableListOf()
        messages = it.getMessagesInDateRange(messages, CommonUtil.getDateXDaysAgo(days), Date(System.currentTimeMillis()))
        val counts = it.getMessageCountByDate(messages)

        for (i in 0 until days) {
            val date = CommonUtil.getDateXDaysAgo(days - 1 - i)
            val name = SimpleDateFormat("MM/dd").format(date)
            var count: Float? = counts[name]?.toFloat()
            if (count == null) {

                count = 0.0f
            }
            entries.add(Pair(name, Entry(i.toFloat(), count)))
        }

        getView()?.showChartCard(title, messages.count().toString(), entries, animateX)
    }
}
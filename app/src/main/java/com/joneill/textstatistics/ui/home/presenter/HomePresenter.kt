package com.joneill.textstatistics.ui.home.presenter

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.google.gson.Gson
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.presenter.BasePresenter
import com.joneill.textstatistics.ui.home.interactor.HomeMVPInteractor
import com.joneill.textstatistics.ui.home.view.HomeMVPView
import com.joneill.textstatistics.util.CommonUtil
import io.reactivex.observers.DisposableCompletableObserver
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


open class HomePresenter<V : HomeMVPView, I : HomeMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V, I>(interactor = interactor), HomeMVPPresenter<V, I> {

    override fun onViewPrepared() {
        interactor!!.loadData().subscribe(object : DisposableCompletableObserver() {
            override fun onComplete() {
                onDataLoaded()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }

    private fun onDataLoaded() = interactor?.let { it ->
        getView()?.showDashboard()

        val msgs = it.getMessagesInDateRange(it.getMessages(), CommonUtil.getDateXDaysAgo(30), Date(System.currentTimeMillis()))
        getView()?.displayTopContactsList(it.getContactsSortedByMessageCount(msgs).filter{it.first != null}.take(5))
        this.loadChart("Texts Sent (Weekly)", 7, false)
        this.loadTotalMessagesCard()
    }

    private fun loadChart(title: String, days: Long, animateX: Boolean) = interactor?.let {
        val messages = it.getMessagesInDateRange(it.getMessages(), CommonUtil.getDateXDaysAgo(days), Date(System.currentTimeMillis()))
        val comparisonsList : MutableList<LineDataSet> = mutableListOf()
        val sentEntries: MutableList<Entry> = mutableListOf()
        val receivedEntries: MutableList<Entry> = mutableListOf()
        val xAxisLabels : MutableList<String> = mutableListOf()

        val sentMessages = messages.filter { message -> message.sent}
        val receivedMessages = messages.minus(sentMessages)

        val sentCounts = it.getMessageCountByDate(sentMessages)
        val receivedCounts = it.getMessageCountByDate(receivedMessages)

        for (i in 0 until days) {
            val date = CommonUtil.getDateXDaysAgo(days - 1 - i)
            val xLabel = SimpleDateFormat("MM/dd").format(date)
            var sentCount: Float? = sentCounts[xLabel]?.toFloat()
            var receivedCount: Float? = receivedCounts[xLabel]?.toFloat()

            if(sentCount == null) sentCount = 0.0f
            if(receivedCount == null) receivedCount = 0.0f

            sentEntries.add(Entry(i.toFloat(), sentCount))
            receivedEntries.add(Entry(i.toFloat(), receivedCount))
            xAxisLabels.add(xLabel)
        }

        comparisonsList.add(LineDataSet(sentEntries, ""))
        comparisonsList.add(LineDataSet(receivedEntries, ""))

        getView()?.showChartCard(title, messages.count().toString(), comparisonsList, xAxisLabels, animateX)
    }

    private fun loadTotalMessagesCard() = interactor.let {
        var messageCount = it?.getMessages()?.size

        if (messageCount == null) {
            messageCount = 0
        }
        getView()?.showTotalMessagesCard(messageCount)
    }

    override fun onContactItemClick(contact: Contact) {
        val gson = Gson()
        interactor!!.setCurrentContact(gson.toJson(contact))
        //getView()?.openContactDataFragment(contact)
        getView()?.openStatsChartFragment()
    }

    override fun onTabSelected(pos: Int) {
        if (pos == 0) {
            loadChart("Texts Sent (Weekly)", 7, false)
        } else {
            loadChart("Texts Sent (Monthly)", 30, true)
        }
    }


}
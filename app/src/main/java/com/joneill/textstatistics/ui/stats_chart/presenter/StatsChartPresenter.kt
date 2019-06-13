package com.joneill.textstatistics.ui.stats_chart.presenter

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.joneill.textstatistics.ui.base.presenter.BasePresenter
import com.joneill.textstatistics.ui.stats_chart.interactor.StatsChartMVPInteractor
import com.joneill.textstatistics.ui.stats_chart.view.StatsChartMVPView
import com.joneill.textstatistics.util.CommonUtil
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject



open class StatsChartPresenter<V : StatsChartMVPView, I : StatsChartMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V, I>(interactor = interactor), StatsChartMVPPresenter<V, I> {
     override fun onViewPrepared() {
        this.loadData(7)
    }

    private fun loadData(days : Long) = interactor?.let {
        val messages = it.getMessagesInDateRange(it.getMessages(), CommonUtil.getDateXDaysAgo(days), Date(System.currentTimeMillis()))

        val comparisonsList : MutableList<LineDataSet> = mutableListOf()
        val entries: MutableList<Entry> = mutableListOf()
        val xAxisLabels : MutableList<String> = mutableListOf()

        val dataPoints = it.getMessageCountByDate(messages)

        for (i in 0 until days) {
            val date = CommonUtil.getDateXDaysAgo(days - 1 - i)
            val xLabel = SimpleDateFormat("MM/dd").format(date)
            var dataPoint: Float? = dataPoints[xLabel]?.toFloat()

            if(dataPoint == null) dataPoint = 0.0f

            entries.add(Entry(i.toFloat(), dataPoint))
            xAxisLabels.add(xLabel)
        }

        comparisonsList.add(LineDataSet(entries, ""))
        getView()?.setXLabels(xAxisLabels)
        getView()?.addSetsToChart(comparisonsList, false)
    }

    override fun test() = interactor?.let {
        val fromDate : Long = 365
        val toDate : Long = 600
        var messages = it.getMessagesInDateRange(it.getMessages(), CommonUtil.getDateXDaysAgo(fromDate), Date(toDate))
        messages = messages.filter {it.sent}

        val comparisonsList : MutableList<LineDataSet> = mutableListOf()
        val entries: MutableList<Entry> = mutableListOf()
        val xAxisLabels : MutableList<String> = mutableListOf()

        val dataPoints = it.getMessageCountByDate(messages)

        for (i in fromDate until toDate) {
            val date = CommonUtil.getDateXDaysAgo(toDate - 1 - i)
            val xLabel = SimpleDateFormat("MM/dd").format(date)
            var dataPoint: Float? = dataPoints[xLabel]?.toFloat()

            if(dataPoint == null) dataPoint = 0.0f

            entries.add(Entry(i.toFloat(), dataPoint))
            xAxisLabels.add(xLabel)
        }

        comparisonsList.add(LineDataSet(entries, ""))
        getView()?.setXLabels(xAxisLabels)
        getView()?.addSetsToChart(comparisonsList, false)
    }
}
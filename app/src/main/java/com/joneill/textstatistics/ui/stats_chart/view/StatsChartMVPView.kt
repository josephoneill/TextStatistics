package com.joneill.textstatistics.ui.stats_chart.view

import com.github.mikephil.charting.data.LineDataSet
import com.joneill.textstatistics.ui.base.view.MVPView

interface StatsChartMVPView : MVPView {
    fun addSetToChart(dataSet : LineDataSet)
    fun addSetsToChart(dataSets: List<LineDataSet>)
}
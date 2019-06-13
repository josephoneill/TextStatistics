package com.joneill.textstatistics.ui.stats_chart.view

import com.github.mikephil.charting.data.LineDataSet
import com.joneill.textstatistics.ui.base.view.MVPView

interface StatsChartMVPView : MVPView {
    fun addSetToChart(dataSet: LineDataSet, animateX : Boolean)
    fun addSetsToChart(dataSets: List<LineDataSet>, animateX : Boolean)
    fun setXLabels(xLabels: List<String>)
}
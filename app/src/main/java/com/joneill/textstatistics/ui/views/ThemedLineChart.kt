package com.joneill.textstatistics.ui.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.joneill.textstatistics.R
import com.joneill.textstatistics.util.CommonUtil

class ThemedLineChart : LineChart {
    constructor(context : Context) : super(context)
    constructor(context : Context?, attrs: AttributeSet) : super(context, attrs)
    constructor(context : Context?, attrs: AttributeSet, defStyle : Int) : super(context, attrs, defStyle)

    override fun init() {
        super.init()

        val textColor = CommonUtil.getAttributeColor(R.attr.colorText, context)

        this.setBackgroundColor(CommonUtil.getAttributeColor(R.attr.colorCardBackground, context))
        this.setDrawGridBackground(false)
        this.setDrawBorders(false)
        this.description.isEnabled = false
        this.legend.isEnabled = false
        this.xAxis.granularity = 1f
        this.xAxis.setDrawGridLines(false)
        this.xAxis.textColor = textColor
        this.xAxis.position = XAxis.XAxisPosition.BOTTOM
        this.axisRight.setDrawAxisLine(false)
        this.axisRight.setDrawLabels(false)
        this.axisRight.setDrawGridLines(false)
        this.xAxis.spaceMin = 0.3f
        this.axisRight.setDrawAxisLine(false)
        this.axisRight.setDrawLabels(false)
        this.axisRight.setDrawGridLines(false)
        this.xAxis.spaceMax = 0.3f
        this.axisRight.textColor = textColor
        this.axisLeft.setDrawAxisLine(false)
        this.axisLeft.textColor = textColor
        this.axisLeft.setDrawGridLines(false)
        this.setTouchEnabled(false)
        this.setPinchZoom(false)
        this.extraBottomOffset = 15.0f
    }

    fun addDataSet(yVals: List<Entry>, label : String) {
        val dataSet = LineDataSet(yVals, label)
        this.styleDataSet(dataSet)
        this.data = LineData(dataSet)
    }

    private fun styleDataSet(dataSet : LineDataSet) {
        val accent = CommonUtil.getAttributeColor(R.attr.colorAccent, context)
        dataSet.color = accent
        dataSet.setDrawValues(false)
        dataSet.lineWidth = 4.0f
        dataSet.circleRadius = 0.01f
        dataSet.circleColors = mutableListOf(Color.TRANSPARENT)
        dataSet.circleHoleColor = accent
    }
}
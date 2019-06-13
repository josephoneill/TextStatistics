package com.joneill.textstatistics.ui.stats_chart.presenter

import com.joneill.textstatistics.ui.base.presenter.MVPPresenter
import com.joneill.textstatistics.ui.stats_chart.interactor.StatsChartMVPInteractor
import com.joneill.textstatistics.ui.stats_chart.view.StatsChartMVPView

interface StatsChartMVPPresenter<V : StatsChartMVPView, I : StatsChartMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
    fun test() : Unit?
}
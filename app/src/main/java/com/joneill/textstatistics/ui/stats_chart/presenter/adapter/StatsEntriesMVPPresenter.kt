package com.joneill.textstatistics.ui.stats_chart.presenter.adapter

import com.joneill.textstatistics.ui.base.presenter.MVPPresenter
import com.joneill.textstatistics.ui.stats_chart.interactor.StatsChartMVPInteractor
import com.joneill.textstatistics.ui.stats_chart.view.adapter.StatsEntriesMVPView


interface StatsEntriesMVPPresenter<V : StatsEntriesMVPView, I : StatsChartMVPInteractor> : MVPPresenter<V, I> {
    fun onBind(holder : StatsEntriesMVPView, position : Int)
    fun getItemCount() : Int
}
package com.joneill.textstatistics.ui.stats_chart.presenter

import com.joneill.textstatistics.ui.base.presenter.BasePresenter
import com.joneill.textstatistics.ui.stats_chart.interactor.StatsChartMVPInteractor
import com.joneill.textstatistics.ui.stats_chart.view.StatsChartMVPView
import javax.inject.Inject



open class StatsChartPresenter<V : StatsChartMVPView, I : StatsChartMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V, I>(interactor = interactor), StatsChartMVPPresenter<V, I> {
     override fun onViewPrepared() {

    }
}
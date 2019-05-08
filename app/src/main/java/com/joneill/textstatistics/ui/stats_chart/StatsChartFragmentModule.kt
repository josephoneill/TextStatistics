package com.joneill.textstatistics.ui.stats_chart

import androidx.recyclerview.widget.LinearLayoutManager
import com.joneill.textstatistics.ui.stats_chart.interactor.StatsChartInteractor
import com.joneill.textstatistics.ui.stats_chart.interactor.StatsChartMVPInteractor
import com.joneill.textstatistics.ui.stats_chart.presenter.StatsChartMVPPresenter
import com.joneill.textstatistics.ui.stats_chart.presenter.StatsChartPresenter
import com.joneill.textstatistics.ui.stats_chart.presenter.adapter.StatsEntriesMVPPresenter
import com.joneill.textstatistics.ui.stats_chart.presenter.adapter.StatsEntriesPresenter
import com.joneill.textstatistics.ui.stats_chart.view.StatsChartFragment
import com.joneill.textstatistics.ui.stats_chart.view.StatsChartMVPView
import com.joneill.textstatistics.ui.stats_chart.view.adapter.StatsEntriesAdapter
import com.joneill.textstatistics.ui.stats_chart.view.adapter.StatsEntriesMVPView
import dagger.Module
import dagger.Provides

@Module
class StatsChartFragmentModule {

    @Provides
    internal fun provideStatsChartInteractor(statsChartInteractor: StatsChartInteractor): StatsChartMVPInteractor = statsChartInteractor

    @Provides
    internal fun provideStatsChartPresenter(statsChartPresenter: StatsChartPresenter<StatsChartMVPView, StatsChartMVPInteractor>)
            : StatsChartMVPPresenter<StatsChartMVPView, StatsChartMVPInteractor> = statsChartPresenter

    @Provides
    internal fun provideStatsEntriesPresenter(statsEntriesPresenter: StatsEntriesPresenter<StatsEntriesMVPView, StatsChartMVPInteractor>)
            : StatsEntriesMVPPresenter<StatsEntriesMVPView, StatsChartMVPInteractor> = statsEntriesPresenter

    @Provides
    internal fun provideStatsEntriesAdapter(): StatsEntriesAdapter = StatsEntriesAdapter(null)

    @Provides
    internal fun provideLinearLayoutManager(fragment: StatsChartFragment): LinearLayoutManager = LinearLayoutManager(fragment.activity)
}
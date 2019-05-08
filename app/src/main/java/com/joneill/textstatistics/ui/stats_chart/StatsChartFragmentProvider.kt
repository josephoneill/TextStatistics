package com.joneill.textstatistics.ui.stats_chart

import com.joneill.textstatistics.ui.stats_chart.view.StatsChartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class StatsChartFragmentProvider {

    @ContributesAndroidInjector(modules = [StatsChartFragmentModule::class])
    internal abstract fun provideStatsChartFragment(): StatsChartFragment

}
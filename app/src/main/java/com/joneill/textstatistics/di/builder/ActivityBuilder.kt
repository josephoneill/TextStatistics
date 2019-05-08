package com.joneill.textstatistics.di.builder

import com.joneill.textstatistics.ui.contactdata.ContactDataFragmentProvider
import com.joneill.textstatistics.ui.home.HomeFragmentProvider
import com.joneill.textstatistics.ui.main.MainActivityModule
import com.joneill.textstatistics.ui.main.view.MainActivity
import com.joneill.textstatistics.ui.stats_chart.StatsChartFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (HomeFragmentProvider::class), (ContactDataFragmentProvider::class), (StatsChartFragmentProvider::class)])
    abstract fun bindMainActivity(): MainActivity
}
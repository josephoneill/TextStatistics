package com.joneill.textstatistics.di.builder

import com.joneill.textstatistics.ui.home.ContactDataFragmentProvider
import com.joneill.textstatistics.ui.home.HomeFragmentProvider
import com.joneill.textstatistics.ui.main.MainActivityModule
import com.joneill.textstatistics.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (HomeFragmentProvider::class), (ContactDataFragmentProvider::class)])
    abstract fun bindMainActivity(): MainActivity
}
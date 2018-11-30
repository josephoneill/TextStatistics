package com.joneill.textstatistics.ui.home

import com.joneill.textstatistics.ui.ContactData.ContactDataFragmentModule
import com.joneill.textstatistics.ui.ContactData.`ContactDataFragmentModule_ProvideContactDataInteractor$app_debugFactory`
import com.joneill.textstatistics.ui.home.view.ContactDataFragment
import com.joneill.textstatistics.ui.home.view.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ContactDataFragmentProvider {

    @ContributesAndroidInjector(modules = [ContactDataFragmentModule::class])
    internal abstract fun provideContactDataFragment(): ContactDataFragment

}
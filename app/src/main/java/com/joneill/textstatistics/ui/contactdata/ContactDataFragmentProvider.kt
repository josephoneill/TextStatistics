package com.joneill.textstatistics.ui.contactdata

import com.joneill.textstatistics.ui.contactdata.view.ContactDataFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ContactDataFragmentProvider {

    @ContributesAndroidInjector(modules = [ContactDataFragmentModule::class])
    internal abstract fun provideContactDataFragment(): ContactDataFragment

}
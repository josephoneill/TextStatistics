package com.joneill.textstatistics.di.module

import android.app.Application
import android.content.Context
import com.joneill.textstatistics.BuildConfig
import com.joneill.textstatistics.data.preferences.AppPreferenceHelper
import com.joneill.textstatistics.data.preferences.PreferenceHelper
import com.joneill.textstatistics.data.text.AppTextDataHelper
import com.joneill.textstatistics.data.text.TextDataHelper
import com.joneill.textstatistics.di.PreferenceInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @PreferenceInfo
    internal fun provideprefFileName(): String = ""

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper = appPreferenceHelper

    @Provides
    @Singleton
    internal fun provideTextHelper(appTextDataHelper : AppTextDataHelper) : TextDataHelper = appTextDataHelper

}
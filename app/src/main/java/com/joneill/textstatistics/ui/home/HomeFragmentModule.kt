package com.joneill.textstatistics.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.joneill.textstatistics.ui.home.interactor.HomeMVPInteractor
import com.joneill.textstatistics.ui.home.presenter.HomeMVPPresenter
import com.joneill.textstatistics.ui.home.presenter.HomePresenter
import com.joneill.textstatistics.ui.home.presenter.adapter.TopContactsMVPPresenter
import com.joneill.textstatistics.ui.home.presenter.adapter.TopContactsPresenter
import com.joneill.textstatistics.ui.home.view.HomeFragment
import com.joneill.textstatistics.ui.home.view.HomeMVPView
import com.joneill.textstatistics.ui.home.view.adapter.TopContactsAdapter
import com.joneill.textstatistics.ui.home.view.adapter.TopContactsMVPView
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentModule {

    @Provides
    internal fun provideHomeInteractor(homeInteractor: com.joneill.textstatistics.ui.home.interactor.HomeInteractor): HomeMVPInteractor = homeInteractor

    @Provides
    internal fun provideHomePresenter(homePresenter: HomePresenter<HomeMVPView, HomeMVPInteractor>)
            : HomeMVPPresenter<HomeMVPView, HomeMVPInteractor> = homePresenter

    @Provides
    internal fun provideTopContactsPresenter(topContactsPresenter: TopContactsPresenter<TopContactsMVPView, HomeMVPInteractor>)
            : TopContactsMVPPresenter<TopContactsMVPView, HomeMVPInteractor> = topContactsPresenter

    @Provides
    internal fun provideTopContactsAdapter(): TopContactsAdapter = TopContactsAdapter(null)

    @Provides
    internal fun provideLinearLayoutManager(fragment: HomeFragment): LinearLayoutManager = LinearLayoutManager(fragment.activity)
}
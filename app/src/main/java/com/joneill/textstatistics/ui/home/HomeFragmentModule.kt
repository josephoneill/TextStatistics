package com.joneill.textstatistics.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.joneill.textstatistics.ui.home.view.HomeFragment
import com.joneill.textstatistics.ui.home.view.HomeMVPView
import com.joneill.textstatistics.ui.home.view.ContactsAdapter
import com.joneill.textstatistics.ui.main.interactor.HomeInteractor
import com.joneill.textstatistics.ui.main.interactor.HomeMVPInteractor
import com.joneill.textstatistics.ui.main.presenter.HomeMVPPresenter
import com.joneill.textstatistics.ui.main.presenter.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentModule {

    @Provides
    internal fun provideHomeInteractor(homeInteractor: HomeInteractor): HomeMVPInteractor = homeInteractor

    @Provides
    internal fun provideHomePresenter(homePresenter: HomePresenter<HomeMVPView, HomeMVPInteractor>)
            : HomeMVPPresenter<HomeMVPView, HomeMVPInteractor> = homePresenter

    @Provides
    internal fun provideContactsAdapter(): ContactsAdapter = ContactsAdapter(ArrayList())

    @Provides
    internal fun provideLinearLayoutManager(fragment: HomeFragment): LinearLayoutManager = LinearLayoutManager(fragment.activity)

}
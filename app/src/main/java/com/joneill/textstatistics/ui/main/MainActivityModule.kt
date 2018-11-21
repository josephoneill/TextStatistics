package com.joneill.textstatistics.ui.main

import androidx.recyclerview.widget.LinearLayoutManager
import com.joneill.textstatistics.ui.main.interactor.MainInteractor
import com.joneill.textstatistics.ui.main.interactor.MainMVPInteractor
import com.joneill.textstatistics.ui.main.presenter.MainMVPPresenter
import com.joneill.textstatistics.ui.main.presenter.HomePresenter
import com.joneill.textstatistics.ui.home.view.ContactsAdapter
import com.joneill.textstatistics.ui.main.presenter.MainPresenter
import com.joneill.textstatistics.ui.main.view.MainActivity
import com.joneill.textstatistics.ui.main.view.MainMVPView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): MainMVPInteractor = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainMVPView, MainMVPInteractor>)
            : MainMVPPresenter<MainMVPView, MainMVPInteractor> = mainPresenter

}
package com.joneill.textstatistics.ui.contactdata

import androidx.recyclerview.widget.LinearLayoutManager
import com.joneill.textstatistics.ui.contactdata.view.ContactDataAdapter
import com.joneill.textstatistics.ui.contactdata.view.ContactDataFragment
import com.joneill.textstatistics.ui.contactdata.view.ContactDataMVPView
import com.joneill.textstatistics.ui.contactdata.interactor.ContactDataInteractor
import com.joneill.textstatistics.ui.contactdata.interactor.ContactDataMVPInteractor
import com.joneill.textstatistics.ui.contactdata.presenter.ContactDataMVPPresenter
import com.joneill.textstatistics.ui.contactdata.presenter.ContactDataPresenter
import dagger.Module
import dagger.Provides

@Module
class ContactDataFragmentModule {

    @Provides
    internal fun provideContactDataInteractor(contactDataInteractor: ContactDataInteractor): ContactDataMVPInteractor = contactDataInteractor

    @Provides
    internal fun provideContactDataPresenter(contactDataPresenter: ContactDataPresenter<ContactDataMVPView, ContactDataMVPInteractor>)
            : ContactDataMVPPresenter<ContactDataMVPView, ContactDataMVPInteractor> = contactDataPresenter

    @Provides
    internal fun provideContactsAdapter(): ContactDataAdapter = ContactDataAdapter(ArrayList())

    @Provides
    internal fun provideLinearLayoutManager(fragment: ContactDataFragment): LinearLayoutManager = LinearLayoutManager(fragment.activity)
}
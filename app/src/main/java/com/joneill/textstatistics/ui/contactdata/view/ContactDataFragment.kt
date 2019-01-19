package com.joneill.textstatistics.ui.home.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.view.BaseFragment
import com.joneill.textstatistics.ui.contactdata.view.ContactDataAdapter
import com.joneill.textstatistics.ui.main.interactor.ContactDataMVPInteractor
import com.joneill.textstatistics.ui.main.presenter.ContactDataMVPPresenter
import kotlinx.android.synthetic.main.fragment_contact_data.*
import javax.inject.Inject
import javax.inject.Provider


class ContactDataFragment : BaseFragment(), ContactDataMVPView {

    @Inject
    internal lateinit var contactDataAdapter: ContactDataAdapter
    @Inject
    internal lateinit var layoutManager: Provider<LinearLayoutManager>
    @Inject
    internal lateinit var presenter: ContactDataMVPPresenter<ContactDataMVPView, ContactDataMVPInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        contact_data_recycler_view.layoutManager = layoutManager.get()
        contact_data_recycler_view.itemAnimator = DefaultItemAnimator()
        contact_data_recycler_view.adapter = contactDataAdapter
        presenter.onViewPrepared()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun displayMessagesList(messages: List<Message>?) = messages?.let {
        contactDataAdapter.addMessagesToList(it)
    }
}
package com.joneill.textstatistics.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.text.Contact
import com.joneill.textstatistics.ui.base.view.BaseFragment
import com.joneill.textstatistics.ui.main.interactor.HomeMVPInteractor
import com.joneill.textstatistics.ui.main.presenter.HomeMVPPresenter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeMVPView {
    @Inject
    internal lateinit var contactsAdapter: ContactsAdapter
    @Inject
    internal lateinit var layoutManager: LinearLayoutManager
    @Inject
    internal lateinit var presenter: HomeMVPPresenter<HomeMVPView, HomeMVPInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        contacts_recycler_view.layoutManager = layoutManager
        contacts_recycler_view.itemAnimator = DefaultItemAnimator()
        contacts_recycler_view.adapter = contactsAdapter
        presenter.onViewPrepared()
    }

    override fun displayContactsList(contacts: List<Contact>?) = contacts?.let {
        contactsAdapter.addContactsToList(it)
    }

}
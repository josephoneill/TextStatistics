package com.joneill.textstatistics.ui.home.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.text.Contact
import com.joneill.textstatistics.ui.base.view.BaseFragment
import com.joneill.textstatistics.ui.home.view.adapter.ContactsAdapter
import com.joneill.textstatistics.ui.home.view.adapter.OnContactItemClickListener
import com.joneill.textstatistics.ui.main.interactor.HomeMVPInteractor
import com.joneill.textstatistics.ui.main.presenter.HomeMVPPresenter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import javax.inject.Provider

class HomeFragment : BaseFragment(), HomeMVPView {

    @Inject
    internal lateinit var contactsAdapter: ContactsAdapter
    @Inject
    internal lateinit var layoutManager: Provider<LinearLayoutManager>
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
        contactsAdapter.onRecyclerItemLongClickListener = object : OnContactItemClickListener {
            override fun onItemClick(contact : Contact) {
                presenter.onContactItemClick(contact)
            }
        }
        home_recycler_view.layoutManager = layoutManager.get()
        home_recycler_view.itemAnimator = DefaultItemAnimator()
        home_recycler_view.adapter = contactsAdapter

        activity?.actionBar?.title = "Home"
        presenter.onViewPrepared()
    }

    override fun displayContactsList(contacts: List<Contact>?) = contacts?.let {
        contactsAdapter.addContactsToList(it)
        home_recycler_view.visibility = View.VISIBLE
        home_progress_bar.visibility = View.GONE
    }

    override fun openContactDataFragment(contact : Contact) {
        val fragment = ContactDataFragment()
        val transaction : FragmentTransaction = activity!!.supportFragmentManager.beginTransaction()

        transaction.replace(R.id.frame_home_fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
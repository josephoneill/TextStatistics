package com.joneill.textstatistics.ui.home.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.home.presenter.adapter.TopContactsMVPPresenter
import com.joneill.textstatistics.ui.main.interactor.HomeMVPInteractor
import kotlinx.android.synthetic.main.contacts_list.view.*


class TopContactsAdapter(private var presenter : TopContactsMVPPresenter<TopContactsMVPView, HomeMVPInteractor>?,
                         private val map : MutableList<Pair<Contact, Int>>) : RecyclerView.Adapter<TopContactsAdapter.TopContactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopContactsViewHolder {
        return TopContactsViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.contacts_list, parent, false))
    }

    override fun onBindViewHolder(holder: TopContactsViewHolder, position: Int) {
        presenter?.onBind(holder, position)
    }

    override fun getItemCount(): Int {
        if (presenter == null) return 0
        return presenter!!.getItemCount()
    }

    fun setPresenter(presenter : TopContactsMVPPresenter<TopContactsMVPView, HomeMVPInteractor>) {
        this.presenter = presenter
    }

    fun setContactsList(map : List<Pair<Contact?, Int>>) {
        presenter?.setContactsList(map)
        notifyDataSetChanged()
    }

    inner class TopContactsViewHolder(view: View) : RecyclerView.ViewHolder(view), TopContactsMVPView {

        override fun setContactName(name : String?) {
            itemView.text_contact_name_card.text = name
        }

    }
}
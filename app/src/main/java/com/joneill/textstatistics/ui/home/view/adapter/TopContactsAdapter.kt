package com.joneill.textstatistics.ui.home.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.home.interactor.HomeMVPInteractor
import com.joneill.textstatistics.ui.home.presenter.adapter.TopContactsMVPPresenter
import kotlinx.android.synthetic.main.contacts_list.view.*


class TopContactsAdapter(private var presenter: TopContactsMVPPresenter<TopContactsMVPView, HomeMVPInteractor>?) : RecyclerView.Adapter<TopContactsAdapter.TopContactsViewHolder>() {
    var onRecyclerItemClickListener: OnContactItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopContactsViewHolder {
        val viewHolder = TopContactsViewHolder(presenter, LayoutInflater.from(parent.context)
                .inflate(R.layout.contacts_list, parent, false))
        presenter?.onAttach(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: TopContactsViewHolder, position: Int) {
        presenter?.onBind(holder, position)
    }

    override fun getItemCount(): Int {
        if (presenter == null) return 0
        return presenter!!.getItemCount()
    }

    fun setPresenter(presenter: TopContactsMVPPresenter<TopContactsMVPView, HomeMVPInteractor>) {
        this.presenter = presenter
    }

    fun setContactsList(map: List<Pair<Contact?, Int>>) {
        presenter?.setContactsList(map)
        notifyDataSetChanged()
    }

    inner class TopContactsViewHolder(private val presenter: TopContactsMVPPresenter<TopContactsMVPView, HomeMVPInteractor>?, view: View) : RecyclerView.ViewHolder(view), TopContactsMVPView {
        override fun setOnClickListener() {
            itemView.layout_contact_card_holder.setOnClickListener {
                presenter?.onContactClicked(adapterPosition)
            }
        }

        override fun onClick(contact: Contact) {
            onRecyclerItemClickListener?.onItemClick(contact)
        }

        override fun setContactName(name: String?) {
            name?.let {
                itemView.text_name_contact_card.text = it
            }
        }

        override fun setContactMessageCount(count: Int) {
            count.toString().let {
                itemView.text_message_count_contact_card.text = this.itemView.resources.getString(R.string.messages_past_30_days, it)
            }
        }
    }
}
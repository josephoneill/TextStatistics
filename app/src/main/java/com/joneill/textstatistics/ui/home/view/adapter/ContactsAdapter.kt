package com.joneill.textstatistics.ui.home.view.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.text.data.Contact
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.contacts_list.*

class ContactsAdapter(private val contactsList: MutableList<Contact>) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    var onRecyclerItemLongClickListener: OnContactItemClickListener? = null

    override fun getItemCount() = this.contactsList.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) = holder.let {
        it.clear()
        it.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContactsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.contacts_list, parent, false))

    internal fun addContactsToList(contact: List<Contact>) {
        if (!this.contactsList.containsAll(contact)) {
            this.contactsList.addAll(contact)
            notifyDataSetChanged()
        }
    }

    inner class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun clear() {
            text_contact_name_card.text = ""
            text_contact_number_card.text = ""
        }

        fun onBind(position: Int) {
            val contact = contactsList[position]
            inflateData(contact.name, contact.number, contact.profileImage)
            setItemClickListener(contact)
        }

        private fun setItemClickListener(contact: Contact) {
            layout_contact_card_holder.setOnClickListener {
                contact.let {
                    onRecyclerItemLongClickListener?.onItemClick(it)
                }
            }
        }

        private fun inflateData(name: String?, number: String?, profileImage: Bitmap?) {
            name?.let {
                text_contact_name_card.text = it
            }
            number?.let {
                text_contact_number_card.text = it
            }
            profileImage?.let {
                image_contact_card.setImageBitmap(profileImage)
            }
        }
    }
}
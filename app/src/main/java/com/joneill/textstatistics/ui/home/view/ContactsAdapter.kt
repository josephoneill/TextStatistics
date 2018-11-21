package com.joneill.textstatistics.ui.home.view;

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.text.Contact
import kotlinx.android.synthetic.main.contacts_list.view.*

class ContactsAdapter(private val contactsList: MutableList<Contact>) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {
    override fun getItemCount() = this.contactsList.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) = holder.let {
        it.clear()
        it.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContactsViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.contacts_list, parent, false))

    internal fun addContactsToList(contact: List<Contact>) {
        this.contactsList.addAll(contact)
        notifyDataSetChanged()
    }

    inner class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun clear() {
            itemView.text_contact_name_card.text = ""
            itemView.text_contact_number_card.text = ""
        }

        fun onBind(position: Int) {
            val contact = contactsList[position]
            inflateData(contact.name, contact.number, contact.profileImage)
            //setItemClickListener(blogUrl)
        }

        private fun setItemClickListener(blogUrl: String?) {
            itemView.setOnClickListener {
                blogUrl?.let { it ->
                    try {
                        val intent = Intent()
                        // using "with" as an example
                        with(intent) {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse(it)
                            addCategory(Intent.CATEGORY_BROWSABLE)
                        }
                        itemView.context.startActivity(intent)
                    } catch (e: Exception) {
                    }
                }
            }
        }
        private fun inflateData(name: String?, number : String?, profileImage : Bitmap?) {
            name?.let {
                itemView.text_contact_name_card.text = it
            }
            number?.let {
                itemView.text_contact_number_card.text = it
            }
            profileImage.let {
                itemView.image_contact_card.setImageBitmap(profileImage)
            }
        }
    }
}
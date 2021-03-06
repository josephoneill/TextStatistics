package com.joneill.textstatistics.ui.contactdata.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.text.data.Message
import kotlinx.android.synthetic.main.contact_data_list_item.view.*

class ContactDataAdapter(private val messagesList: MutableList<Message>) : RecyclerView.Adapter<ContactDataAdapter.ContactsViewHolder>() {

    override fun getItemCount() = this.messagesList.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) = holder.let {
        it.clear()
        it.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContactsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_data_list_item, parent, false))

    internal fun addMessagesToList(messages: List<Message>) {
        this.messagesList.addAll(messages)
        notifyDataSetChanged()
    }

    inner class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun clear() {
            itemView.text_contact_data_message.text = ""
        }

        fun onBind(position: Int) {
            val message = messagesList[position]
            inflateData(message.data)
            setItemClickListener(message)
        }

        private fun setItemClickListener(message: Message) {
            itemView.layout_contact_data_card_holder.setOnClickListener {
                message.let {

                }
            }
        }

        private fun inflateData(data : String?) {
            data?.let {
                itemView.text_contact_data_message.text = it
            }
        }
    }
}
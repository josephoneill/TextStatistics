package com.joneill.textstatistics.ui.home.view.adapter


import com.joneill.textstatistics.data.text.Contact

interface OnContactItemClickListener {
    fun onItemClick(contact : Contact)
}
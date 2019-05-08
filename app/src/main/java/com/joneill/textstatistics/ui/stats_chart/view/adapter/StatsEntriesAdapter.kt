package com.joneill.textstatistics.ui.stats_chart.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.home.view.adapter.OnContactItemClickListener
import com.joneill.textstatistics.ui.stats_chart.interactor.StatsChartMVPInteractor
import com.joneill.textstatistics.ui.stats_chart.presenter.adapter.StatsEntriesMVPPresenter


class StatsEntriesAdapter(private var presenter: StatsEntriesMVPPresenter<StatsEntriesMVPView, StatsChartMVPInteractor>?) : RecyclerView.Adapter<StatsEntriesAdapter.StatsEntriesViewHolder>() {
    var onRecyclerItemClickListener: OnContactItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsEntriesViewHolder {
        val viewHolder = StatsEntriesViewHolder(presenter, LayoutInflater.from(parent.context)
                .inflate(R.layout.stats_entry_list_item, parent, false))
        presenter?.onAttach(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: StatsEntriesViewHolder, position: Int) {
        presenter?.onBind(holder, position)
    }

    override fun getItemCount(): Int {
        if (presenter == null) return 0
        return presenter!!.getItemCount()
    }

    fun setPresenter(presenter: StatsEntriesMVPPresenter<StatsEntriesMVPView, StatsChartMVPInteractor>) {
        this.presenter = presenter
    }

    inner class StatsEntriesViewHolder(private val presenter: StatsEntriesMVPPresenter<StatsEntriesMVPView, StatsChartMVPInteractor>?, view: View) : RecyclerView.ViewHolder(view), StatsEntriesMVPView {
        override fun setOnClickListener() {

        }

        override fun onClick(contact: Contact) {
            onRecyclerItemClickListener?.onItemClick(contact)
        }
    }
}
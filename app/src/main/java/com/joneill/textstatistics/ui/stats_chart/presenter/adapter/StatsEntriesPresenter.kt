package com.joneill.textstatistics.ui.stats_chart.presenter.adapter

import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.presenter.BasePresenter
import com.joneill.textstatistics.ui.stats_chart.interactor.StatsChartMVPInteractor
import com.joneill.textstatistics.ui.stats_chart.view.adapter.StatsEntriesMVPView
import javax.inject.Inject

open class StatsEntriesPresenter<V : StatsEntriesMVPView, I : StatsChartMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V, I>(interactor = interactor), StatsEntriesMVPPresenter<V, I> {
    private var list : List<Pair<Contact?, Int>> = mutableListOf()

    override fun onBind(holder: StatsEntriesMVPView, position: Int) {
        holder.setOnClickListener()
    }

    override fun getItemCount() : Int = list.size
}
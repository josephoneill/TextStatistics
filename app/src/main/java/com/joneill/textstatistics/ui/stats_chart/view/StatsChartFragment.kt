package com.joneill.textstatistics.ui.stats_chart.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.view.BaseFragment
import com.joneill.textstatistics.ui.home.view.adapter.OnContactItemClickListener
import com.joneill.textstatistics.ui.stats_chart.interactor.StatsChartMVPInteractor
import com.joneill.textstatistics.ui.stats_chart.presenter.StatsChartMVPPresenter
import com.joneill.textstatistics.ui.stats_chart.presenter.adapter.StatsEntriesMVPPresenter
import com.joneill.textstatistics.ui.stats_chart.view.adapter.StatsEntriesAdapter
import com.joneill.textstatistics.ui.stats_chart.view.adapter.StatsEntriesMVPView
import kotlinx.android.synthetic.main.fragment_stats_chart.*
import javax.inject.Inject
import javax.inject.Provider


class StatsChartFragment : BaseFragment(), StatsChartMVPView {

    @Inject
    internal lateinit var statsEntriesAdapter: StatsEntriesAdapter
    @Inject
    internal lateinit var layoutManager: Provider<LinearLayoutManager>
    @Inject
    internal lateinit var presenter: StatsChartMVPPresenter<StatsChartMVPView, StatsChartMVPInteractor>
    @Inject
    internal lateinit var statsEntriesPresenter: StatsEntriesMVPPresenter<StatsEntriesMVPView, StatsChartMVPInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stats_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun init() {
        statsEntriesAdapter.setPresenter(statsEntriesPresenter)
        statsEntriesAdapter.onRecyclerItemClickListener = object : OnContactItemClickListener {
            override fun onItemClick(contact: Contact) {
                // do something eventually
            }
        }
        stats_chart_recycler_view.layoutManager = layoutManager.get()
        stats_chart_recycler_view.itemAnimator = DefaultItemAnimator()
        stats_chart_recycler_view.adapter = statsEntriesAdapter

        test_button.setOnClickListener {
            presenter.test()
        }

        presenter.onViewPrepared()
    }

    override fun setXLabels(xAxisLabels : List<String>) {
        val formatter = IAxisValueFormatter { value, _ ->
            xAxisLabels[value.toInt()]
        }

        stats_line_chart.xAxis.valueFormatter = formatter
    }

    override fun addSetsToChart(dataSets: List<LineDataSet>, animateX : Boolean) {
        if (animateX) {
            stats_line_chart.animateX(1000)
        } else {
            stats_line_chart.animateY(700)
        }
        stats_line_chart.addDataSets(dataSets)
    }

    override fun addSetToChart(dataSet: LineDataSet,animateX : Boolean) {
        if (animateX) {
            stats_line_chart.animateX(1000)
        } else {
            stats_line_chart.animateY(700)
        }
        stats_line_chart.addDataSet(dataSet)
    }
}
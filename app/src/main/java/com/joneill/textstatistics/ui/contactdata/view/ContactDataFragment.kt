package com.joneill.textstatistics.ui.contactdata.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.graph.ComparisonEntrySet
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.view.BaseFragment
import com.joneill.textstatistics.ui.contactdata.interactor.ContactDataMVPInteractor
import com.joneill.textstatistics.ui.contactdata.presenter.ContactDataMVPPresenter
import kotlinx.android.synthetic.main.fragment_contact_data.*
import kotlinx.android.synthetic.main.tabbed_themed_graph_card.view.*
import javax.inject.Inject
import javax.inject.Provider


class ContactDataFragment : BaseFragment(), ContactDataMVPView {

    @Inject
    internal lateinit var contactDataAdapter: ContactDataAdapter
    @Inject
    internal lateinit var layoutManager: Provider<LinearLayoutManager>
    @Inject
    internal lateinit var presenter: ContactDataMVPPresenter<ContactDataMVPView, ContactDataMVPInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        contact_data_recycler_view.layoutManager = layoutManager.get()
        contact_data_recycler_view.itemAnimator = DefaultItemAnimator()
        contact_data_recycler_view.adapter = contactDataAdapter
        presenter.onViewPrepared()
    }

    override fun displayMessagesList(messages: List<Message>?) = messages?.let {
        contactDataAdapter.addMessagesToList(it)
    }

    override fun showChartCard(title: String, dataValue: String, comparisonsList: List<ComparisonEntrySet>, animateX: Boolean) {
        // Set card info
        contact_data_chart_card.setTitle(title)
        contact_data_chart_card.setValue(dataValue)

        val mLineChart = contact_data_chart_card.card_line_chart
        // The labels that should be drawn on the XAxis
        val days = ArrayList<String>()

        val dataSets = ArrayList<LineDataSet>()

        for (comparison in comparisonsList) {
            val isDaysEmpty = days.size == 0
            val dataComp = ArrayList<Entry>()
            for (data in comparison.messageCountsByDate) {
                if(isDaysEmpty) {
                    days.add(data.date)
                }
                dataComp.add(data.countEntry)
            }
            val setComp = LineDataSet(dataComp, comparison.label)
            dataSets.add(setComp)
        }

        val formatter = IAxisValueFormatter { value, _ ->
            days[value.toInt()]
        }

        mLineChart.xAxis.valueFormatter = formatter
        if (animateX) {
            mLineChart.animateX(1000)
        } else {
            mLineChart.animateY(700)
        }
        mLineChart.addDataSet(dataSets)
        mLineChart.invalidate() // refresh
    }
}
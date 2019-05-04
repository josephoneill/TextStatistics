package com.joneill.textstatistics.ui.contactdata.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.view.BaseFragment
import com.joneill.textstatistics.ui.contactdata.interactor.ContactDataMVPInteractor
import com.joneill.textstatistics.ui.contactdata.presenter.ContactDataMVPPresenter
import kotlinx.android.synthetic.main.fragment_contact_data.*
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

    override fun showChartCard(title: String, dataValue: String, dataList: List<Pair<String, Entry>>, animateX: Boolean) {
        // Set card info
        //tv_home_chart_title.text = title
        //tv_home_chart_value.text = dataValue

        val mLineChart = contact_line_chart
        val entries = ArrayList<Entry>()
        // The labels that should be drawn on the XAxis
        val days = ArrayList<String>()
        for (data in dataList) {
            days.add(data.first)
            entries.add(data.second)
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
        mLineChart.addDataSet(entries, "")
        mLineChart.invalidate() // refresh
    }
}
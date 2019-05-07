package com.joneill.textstatistics.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.google.android.material.tabs.TabLayout
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.graph.ComparisonEntrySet
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.view.BaseFragment
import com.joneill.textstatistics.ui.contactdata.view.ContactDataFragment
import com.joneill.textstatistics.ui.home.interactor.HomeMVPInteractor
import com.joneill.textstatistics.ui.home.presenter.HomeMVPPresenter
import com.joneill.textstatistics.ui.home.presenter.adapter.TopContactsMVPPresenter
import com.joneill.textstatistics.ui.home.view.adapter.OnContactItemClickListener
import com.joneill.textstatistics.ui.home.view.adapter.TopContactsAdapter
import com.joneill.textstatistics.ui.home.view.adapter.TopContactsMVPView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.tabbed_themed_graph_card.view.*
import javax.inject.Inject
import javax.inject.Provider

class HomeFragment : BaseFragment(), HomeMVPView {

    @Inject
    internal lateinit var topContactsAdapter: TopContactsAdapter
    @Inject
    internal lateinit var layoutManager: Provider<LinearLayoutManager>
    @Inject
    internal lateinit var presenter: HomeMVPPresenter<HomeMVPView, HomeMVPInteractor>
    @Inject
    internal lateinit var topContactsPresenter: TopContactsMVPPresenter<TopContactsMVPView, HomeMVPInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showDashboard() {
        home_scroll_view?.visibility = View.VISIBLE
        home_progress_bar?.visibility = View.GONE
    }

    override fun setUp() {
        topContactsAdapter.setPresenter(topContactsPresenter)
        topContactsAdapter.onRecyclerItemClickListener = object : OnContactItemClickListener {
            override fun onItemClick(contact: Contact) {
                presenter.onContactItemClick(contact)
            }
        }

        contacts_recycler_view.layoutManager = layoutManager.get()
        contacts_recycler_view.itemAnimator = DefaultItemAnimator()
        contacts_recycler_view.adapter = topContactsAdapter

        activity?.actionBar?.title = "Home"
        setListeners()
        presenter.onViewPrepared()
    }

    override fun displayTopContactsList(list: List<Pair<Contact?, Int>>) {
        topContactsAdapter.setContactsList(list)
    }

    override fun openContactDataFragment(contact: Contact) {

        val fragment = ContactDataFragment()
        val transaction: FragmentTransaction = activity!!.supportFragmentManager.beginTransaction()

        transaction.replace(R.id.main_fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showChartCard(title: String, dataValue: String, comparisonsList: List<ComparisonEntrySet>, animateX: Boolean) {
        // Set card info
        home_chart_card.setTitle(title)
        home_chart_card.setValue(dataValue)

        val mLineChart = home_chart_card.card_line_chart
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

    override fun showTotalMessagesCard(total: Int) {
        tv_home_total_messages_title.text = getString(R.string.total_texts_count)
        tv_home_total_messages_value.text = total.toString()
    }

    private fun setListeners() {
        home_chart_card.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                presenter.onTabSelected(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }
}
package com.joneill.textstatistics.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.Entry
import com.joneill.textstatistics.R
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.view.BaseFragment
import com.joneill.textstatistics.ui.home.view.adapter.ContactsAdapter
import com.joneill.textstatistics.ui.home.view.adapter.OnContactItemClickListener
import com.joneill.textstatistics.ui.main.interactor.HomeMVPInteractor
import com.joneill.textstatistics.ui.main.presenter.HomeMVPPresenter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import javax.inject.Provider
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData
import com.google.android.material.tabs.TabLayout



class HomeFragment : BaseFragment(), HomeMVPView {

    @Inject
    internal lateinit var contactsAdapter: ContactsAdapter
    @Inject
    internal lateinit var layoutManager: Provider<LinearLayoutManager>
    @Inject
    internal lateinit var presenter: HomeMVPPresenter<HomeMVPView, HomeMVPInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        contactsAdapter.onRecyclerItemLongClickListener = object : OnContactItemClickListener {
            override fun onItemClick(contact : Contact) {
                presenter.onContactItemClick(contact)
            }
        }
        home_recycler_view.layoutManager = layoutManager.get()
        home_recycler_view.itemAnimator = DefaultItemAnimator()
        home_recycler_view.adapter = contactsAdapter

        activity?.actionBar?.title = "Home"
        setListeners()
        presenter.onViewPrepared()
    }

    override fun displayContactsList(contacts: List<Contact>?) = contacts?.let {
        contactsAdapter.addContactsToList(it)
        //home_recycler_view.visibility = View.VISIBLE
        home_scroll_view.visibility = View.VISIBLE;
        home_progress_bar.visibility = View.GONE
    }

    override fun openContactDataFragment(contact : Contact) {
        val fragment = ContactDataFragment()
        val transaction : FragmentTransaction = activity!!.supportFragmentManager.beginTransaction()

        transaction.replace(R.id.frame_home_fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showChartCard(title : String, value : String, dataList: List<Pair<String, Entry>>, animateX : Boolean) {
        //Set card info
        tv_home_chart_title.text = title
        tv_home_chart_value.text = value

        val mLineChart = home_line_chart
        val entries = ArrayList<Entry>()
        // the labels that should be drawn on the XAxis
        val days = ArrayList<String>()

        for (data in dataList) {
            days.add(data.first)
            entries.add(data.second)
        }
        val formatter = IAxisValueFormatter { value, axis -> // we don't draw numbers, so no decimal digits needed
            days[value.toInt()]
        }

        val materialRed = ResourcesCompat.getColor(resources, R.color.materialPrimaryRed, null)
        val dataSet = LineDataSet(entries, "") // add entries to dataset
        dataSet.color = materialRed
        dataSet.setDrawValues(false)
        dataSet.lineWidth = 4.0f
        dataSet.circleRadius = 0.01f
        dataSet.circleColors = mutableListOf(Color.TRANSPARENT)
        dataSet.circleHoleColor = materialRed

        val lineData = LineData(dataSet)

        //Themeing
        mLineChart.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, null))
        mLineChart.setDrawGridBackground(false)
        mLineChart.setDrawBorders(false)
        mLineChart.description.isEnabled = false
        mLineChart.legend.isEnabled = false
        mLineChart.xAxis.granularity = 1f // minimum axis-step (interval) is 1
        mLineChart.xAxis.valueFormatter = formatter
        mLineChart.xAxis.setDrawGridLines(false)
        mLineChart.xAxis.textColor = Color.WHITE
        mLineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        mLineChart.xAxis.spaceMin = 0.3f
        mLineChart.xAxis.spaceMax = 0.3f
        mLineChart.axisRight.setDrawAxisLine(false)
        mLineChart.axisRight.setDrawLabels(false)
        mLineChart.axisRight.setDrawGridLines(false)
        mLineChart.axisRight.textColor = Color.WHITE
        mLineChart.axisLeft.setDrawAxisLine(false)
        mLineChart.axisLeft.textColor = Color.WHITE
        mLineChart.axisLeft.setDrawGridLines(false)
        mLineChart.extraBottomOffset = 15.0f
        if(animateX) {
            mLineChart.animateX(1000)
        } else {
            mLineChart.animateY(1000)
        }
        mLineChart.data = lineData
        mLineChart.invalidate() // refresh
    }

    private fun setListeners() {
        home_card_tabs.addOnTabSelectedListener(object :
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
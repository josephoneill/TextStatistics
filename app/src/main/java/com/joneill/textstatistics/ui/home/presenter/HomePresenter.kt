package com.joneill.textstatistics.ui.main.presenter

import com.github.mikephil.charting.data.Entry
import com.joneill.textstatistics.data.text.data.Contact
import com.joneill.textstatistics.ui.base.presenter.BasePresenter
import com.joneill.textstatistics.ui.home.view.HomeMVPView
import com.joneill.textstatistics.ui.main.interactor.HomeMVPInteractor
import javax.inject.Inject
import com.google.gson.Gson
import com.joneill.textstatistics.util.CommonUtil
import java.text.SimpleDateFormat
import java.util.*
import io.reactivex.observers.DisposableCompletableObserver


open class HomePresenter<V : HomeMVPView, I : HomeMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V, I>(interactor = interactor), HomeMVPPresenter<V, I> {

    override fun onViewPrepared() {
        interactor!!.loadData().subscribe(object : DisposableCompletableObserver() {
            override fun onComplete() {
                onDataLoaded()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }

    private fun onDataLoaded() = interactor?.let { it ->
        val contacts = it.getContacts()
        getView()?.displayContactsList(contacts)

        loadChart("Texts Sent (Weekly)", 7, false)
    }

    private fun loadChart(title: String, days: Long, animateX: Boolean) = interactor?.let {
        var messages = it.getMessages()
        var entries: MutableList<Pair<String, Entry>> = mutableListOf()
        messages = it.getMessagesInDateRange(messages, CommonUtil.getDateXDaysAgo(days), Date(System.currentTimeMillis()))
        val counts = it.getMessageCountByDate(messages)

        for (i in 0 until days) {
            val date = CommonUtil.getDateXDaysAgo(days - 1 - i)
            val name = SimpleDateFormat("MM/dd").format(date)
            var count: Float? = counts[name]?.toFloat()
            if (count == null) {
                count = 0.0f
            }
            entries.add(Pair(name, Entry(i.toFloat(), count)))
        }

        getView()?.showChartCard(title, messages.count().toString(), entries, animateX)
    }

    override fun onContactItemClick(contact: Contact) {
        val gson = Gson()
        interactor!!.setCurrentContact(gson.toJson(contact))
        getView()?.openContactDataFragment(contact)
    }

    override fun onTabSelected(pos: Int) {
        if (pos == 0) {
            loadChart("Texts Sent (Weekly)", 7, false)
        } else {
            loadChart("Texts Sent (Monthly)", 30, true)
        }
    }


}
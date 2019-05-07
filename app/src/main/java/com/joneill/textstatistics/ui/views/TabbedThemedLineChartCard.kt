package com.joneill.textstatistics.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.google.android.material.tabs.TabLayout
import com.joneill.textstatistics.R
import kotlinx.android.synthetic.main.tabbed_themed_graph_card.view.*

class TabbedThemedLineChartCard : CardView{
    constructor(context: Context) : super(context){
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init (context: Context) {
        LayoutInflater.from(context)
                .inflate(R.layout.tabbed_themed_graph_card, this, true)
    }

    fun setTitle(title : String) {
        this.tv_home_chart_title.text = title
    }

    fun setValue(value : String) {
        this.tv_home_chart_value.text = value
    }

    fun addOnTabSelectedListener(listener : TabLayout.OnTabSelectedListener) {
        this.home_card_tabs.addOnTabSelectedListener(listener)
    }
}

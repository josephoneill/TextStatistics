package com.joneill.textstatistics.ui.stats_chart.interactor

import com.joneill.textstatistics.data.preferences.PreferenceHelper
import com.joneill.textstatistics.data.repository.MessagesRepository
import com.joneill.textstatistics.data.text.AppTextDataHelper
import com.joneill.textstatistics.data.text.data.Message
import com.joneill.textstatistics.ui.base.interactor.BaseInteractor
import javax.inject.Inject


class StatsChartInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, private val textDataHelper: AppTextDataHelper, private val messagesRepository: MessagesRepository) : BaseInteractor(preferenceHelper = preferenceHelper), StatsChartMVPInteractor {
    override fun getMessages(): List<Message> = messagesRepository.messages
}
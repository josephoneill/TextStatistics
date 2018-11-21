package com.joneill.textstatistics.ui.main.interactor

import com.joneill.textstatistics.data.text.Contact
import com.joneill.textstatistics.ui.base.interactor.MVPInteractor

interface HomeMVPInteractor : MVPInteractor {
    fun getTextData() : List<Contact>?
    fun filterCreatorsContactsOnly(contacts : List<Contact>?) : List<Contact>?
}
package com.joneill.textstatistics.ui.base.presenter

import com.joneill.textstatistics.ui.base.interactor.MVPInteractor
import com.joneill.textstatistics.ui.base.view.MVPView

interface MVPPresenter<V : MVPView, I : MVPInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}
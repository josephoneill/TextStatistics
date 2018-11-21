package com.joneill.textstatistics.ui.main.presenter

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.joneill.textstatistics.ui.base.presenter.BasePresenter
import com.joneill.textstatistics.ui.home.view.HomeMVPView
import com.joneill.textstatistics.ui.main.interactor.HomeMVPInteractor
import com.joneill.textstatistics.ui.main.interactor.MainMVPInteractor
import com.joneill.textstatistics.ui.main.view.MainMVPView
import javax.inject.Inject



open class HomePresenter<V : HomeMVPView, I : HomeMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V, I>(interactor = interactor), HomeMVPPresenter<V, I> {

    override fun onViewPrepared() {
        getConversations()
    }

    override fun onAttach(view: V?) {
        super.onAttach(view)
    }

    private fun getConversations() = interactor?.let {
        //getView()?.inflateUserDetails(userData)
        var creators = it.getTextData()
        creators = it.filterCreatorsContactsOnly(creators)
        getView()?.displayContactsList(creators)
    }

}
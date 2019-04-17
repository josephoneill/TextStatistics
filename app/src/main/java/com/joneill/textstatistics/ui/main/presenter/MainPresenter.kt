package com.joneill.textstatistics.ui.main.presenter

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.joneill.textstatistics.ui.base.presenter.BasePresenter
import com.joneill.textstatistics.ui.main.interactor.MainMVPInteractor
import com.joneill.textstatistics.ui.main.view.MainMVPView
import javax.inject.Inject



class MainPresenter<V : MainMVPView, I : MainMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V, I>(interactor = interactor), MainMVPPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
    }

    override fun requestPermissions() {
        val permissions = arrayOf(android.Manifest.permission.READ_SMS, android.Manifest.permission.READ_CONTACTS)
        //Request permission to read SMS data
        if (ContextCompat.checkSelfPermission(getView()!!.getViewActivity(),
                        Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getView()!!.getViewActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getView()!!.getViewActivity(),
                            Manifest.permission.READ_SMS) || ActivityCompat.shouldShowRequestPermissionRationale(getView()!!.getViewActivity(),
                            Manifest.permission.READ_CONTACTS)) {
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getView()!!.getViewActivity(),
                        permissions,
                        101)
            }
        } else {
            //If we already have permission
            onPermissionsResult()
        }
    }

    override fun onPermissionsResult() {
        getView()?.loadHomeFragment()
    }

    override fun onSettingsMenuClicked() {
        getView()?.loadSettingsFragment()
    }

    override fun getCustomTheme() {
        getView()?.setCustomTheme(interactor?.getIsDarkTheme()!!)
    }


}
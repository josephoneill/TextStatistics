package com.joneill.textstatistics.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.joneill.textstatistics.R
import com.joneill.textstatistics.ui.base.view.BaseActivity
import com.joneill.textstatistics.ui.home.view.HomeFragment
import com.joneill.textstatistics.ui.main.interactor.MainMVPInteractor
import com.joneill.textstatistics.ui.main.presenter.MainMVPPresenter
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

import javax.inject.Inject


class MainActivity : BaseActivity(), MainMVPView, HasSupportFragmentInjector {

    @Inject
    internal lateinit var presenter: MainMVPPresenter<MainMVPView, MainMVPInteractor>
    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun loadHomeFragment() {
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_home_fragment_container, HomeFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStack()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun onFragmentAttached() {

    }


    override fun getViewActivity() = this


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        presenter.onPermissionsResult()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}

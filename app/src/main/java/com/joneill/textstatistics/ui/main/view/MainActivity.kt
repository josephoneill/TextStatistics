package com.joneill.textstatistics.ui.main.view

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.joneill.textstatistics.R
import com.joneill.textstatistics.ui.base.view.BaseActivity
import com.joneill.textstatistics.ui.home.view.HomeFragment
import com.joneill.textstatistics.ui.main.interactor.MainMVPInteractor
import com.joneill.textstatistics.ui.main.presenter.MainMVPPresenter
import com.joneill.textstatistics.ui.settings.view.SettingsFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

import javax.inject.Inject
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), MainMVPView, HasSupportFragmentInjector {
    @Inject
    internal lateinit var presenter: MainMVPPresenter<MainMVPView, MainMVPInteractor>
    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach(this)
        if(savedInstanceState == null) {
            presenter.requestPermissions()
        }
        loadTheme()
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    private fun loadTheme() {
        presenter.getCustomTheme()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_open_settings -> {
                presenter.onSettingsMenuClicked()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun loadHomeFragment() {
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment_container, HomeFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun loadSettingsFragment() {
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment_container, SettingsFragment())
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

    override fun setCustomTheme(isDarkTheme: Boolean) {
        if(isDarkTheme) {
            setTheme(R.style.AppTheme)
        } else {
            setTheme(R.style.LightTheme)
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}

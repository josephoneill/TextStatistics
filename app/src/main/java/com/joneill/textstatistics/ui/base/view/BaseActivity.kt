package com.joneill.textstatistics.ui.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity(), MVPView, BaseFragment.CallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDI()
    }

    private fun performDI() = AndroidInjection.inject(this)

}
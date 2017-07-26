package com.costular.randomco.utils

import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_user_detail.*

/**
 * Created by costular on 15/07/17.
 */
open class BaseActivity : AppCompatActivity() {

    fun setupToolbar(homeEnabled: Boolean = false) {
        setSupportActionBar(toolbar as Toolbar)
    }

    fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    fun setToolbarTransparent() {
        toolbar.setBackgroundColor(ContextCompat.getColor(applicationContext,
                android.R.color.transparent))
    }

    fun setWindowTransparent() {
        val w = window // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}
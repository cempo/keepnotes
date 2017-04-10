package com.keepnotes.ui

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

open class BaseActivity : AppCompatActivity() {
    var isActivityVisible = false

    fun setActionBar(toolbar: Toolbar, homeAsUp: Boolean, homeEnabled: Boolean, title: String?) {

        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(homeAsUp)
            actionBar.setHomeButtonEnabled(homeEnabled)
            if (title == null) {
                actionBar.setDisplayShowTitleEnabled(false)
            }

            //if (!TextUtils.isEmpty(title)) {
            actionBar.title = title
            //}
        }

    }

    override fun onResume() {
        super.onResume()
        isActivityVisible = true
    }

    override fun onPause() {
        isActivityVisible = false
        super.onPause()
    }
}
package com.keepnotes.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
    public boolean isActivityVisible = false;

    public void setActionBar(Toolbar toolbar, boolean homeAsUp, boolean homeEnabled, String title) {

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(homeAsUp);
            actionBar.setHomeButtonEnabled(homeEnabled);
            if (title == null) {
                actionBar.setDisplayShowTitleEnabled(false);
            }

            //if (!TextUtils.isEmpty(title)) {
            actionBar.setTitle(title);
            //}
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        isActivityVisible = true;
    }

    @Override
    protected void onPause() {
        isActivityVisible = false;
        super.onPause();
    }
}
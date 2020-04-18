package com.example.blade.activity.web;

import androidx.appcompat.app.ActionBar;

public class webAA {
    public static void ScrollChangeListener(ActionBar actionBar, boolean mac) {
        if (actionBar != null) {
            if (mac) {
                actionBar.show();
            } else {
                actionBar.hide();
            }
        }
    }


}

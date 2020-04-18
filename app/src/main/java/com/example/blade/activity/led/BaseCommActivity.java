package com.example.blade.activity.led;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//@EnableDragToClose()
//@SetDragParameter(enableSwipeUpToHome = true)
public abstract class BaseCommActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutRes());
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract @LayoutRes
    int setLayoutRes();

}
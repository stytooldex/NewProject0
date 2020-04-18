package com.example.blade.base;


import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.blade.R;

//@EnableDragToClose()
//@SetDragParameter(enableSwipeUpToHome = true)
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        addContent();
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract @LayoutRes
    int setLayoutRes();

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void addContent() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FrameLayout flContent = findViewById(R.id.FrameLab);
        View content = View.inflate(this, setLayoutRes(), null);
        if (content != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            flContent.addView(content, params);
        }
    }


}

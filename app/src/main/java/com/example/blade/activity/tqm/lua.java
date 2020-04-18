package com.example.blade.activity.tqm;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.blade.R;
import com.example.blade.base.BaseActivity;
import com.example.blade.util.Code;


public class lua extends BaseActivity {


    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        FragmentUtils.replace(getSupportFragmentManager(), new SettingsFragment(), R.id.settings);
        ToastUtils.showLong("聊天界面不自动发,长按+音量键");
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.settings_activity;
    }


    // --Commented out by Inspection (2018/8/6 10:07):Context mContext = null;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preferences, rootKey);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            Code.findPre(this, "donate_alia", new Code.OnPreferenceClick() {
                @Override
                public void onClick(Preference preference, Object newValue) {
                    Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    startActivity(intent);
                }
            });

        }

    }

}

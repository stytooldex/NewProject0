package com.example.blade.service;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.SPUtils;
import com.example.blade.util.accessibility.BaseAccessibilityService;

import java.util.Random;

import static com.blankj.utilcode.util.ColorUtils.getRandomColor;
import static com.example.blade.ConstantKt.spName;

public class Information_Repeater extends BaseAccessibilityService {

    private final String id = SPUtils.getInstance(spName).getString("flutter.user_description", "??");
    private final Random ran = new Random();

    //tricia[ran.nextInt(tricia.length)]
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        super.onAccessibilityEvent(event);
        if (event.getSource() != null) {

            AccessibilityNodeInfo source = event.getSource();

            if ("android.widget.EditText".equals(event.getClassName().toString()) && SP("flutter.user_Message", false) && SP("ditText", false)) {

                if (SP("flutter.user_fAsTcyCle", false)) {
                    while (SP("ditText", true)) {
                        input(source, id);
                    }
                } else {
                    input(source, id);
                }


            }
            if (event.getClassName().toString().equals("android.widget.EditText")) {
                SP(true);
                //Log.e("1", "Kii");
            } else {
                SP(false);
            }
        }

    }

    public void input(AccessibilityNodeInfo nodeInfo, String text) {
        if (SP("flutter.Random_number", false)) {
            inputText(nodeInfo, String.valueOf(getRandomColor()));
            clickTextViewByText("发送");
        } else {
            inputText(nodeInfo, text);
            clickTextViewByText("发送");
        }

    }

    private boolean SP(@NonNull final String key, final boolean defaultValue) {
        return SPUtils.getInstance(spName).getBoolean(key, defaultValue);
    }

    private void SP(final boolean key) {
        SPUtils.getInstance(spName).put("ditText", key);
    }

}

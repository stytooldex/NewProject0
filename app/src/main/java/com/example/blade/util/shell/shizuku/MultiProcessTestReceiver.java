package com.example.blade.util.shell.shizuku;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import moe.shizuku.api.ShizukuService;

public class MultiProcessTestReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("ShizukuSample", "MultiProcessTestReceiver onReceive 黏合剂: " + ShizukuService.getBinder());
    }
}
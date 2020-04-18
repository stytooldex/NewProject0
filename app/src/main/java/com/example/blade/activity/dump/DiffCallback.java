package com.example.blade.activity.dump;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.blankj.utilcode.util.AppUtils;

public class DiffCallback  extends DiffUtil.ItemCallback<AppUtils.AppInfo> {

    @Override
    public boolean areItemsTheSame(@NonNull AppUtils.AppInfo oldItem, @NonNull AppUtils.AppInfo newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull AppUtils.AppInfo oldItem, @NonNull AppUtils.AppInfo newItem) {
        return false;
    }
}
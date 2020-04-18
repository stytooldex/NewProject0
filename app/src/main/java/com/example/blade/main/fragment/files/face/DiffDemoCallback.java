package com.example.blade.main.fragment.files.face;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.io.File;

public class DiffDemoCallback extends DiffUtil.ItemCallback<File> {

    /**
     * 判断是否是同一个item
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    @Override
    public boolean areItemsTheSame(@NonNull File oldItem, @NonNull File newItem) {
        return false;
    }

    /**
     * 当是同一个item时，再判断内容是否发生改变
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    @Override
    public boolean areContentsTheSame(@NonNull File oldItem, @NonNull File newItem) {
        return false;
    }
}
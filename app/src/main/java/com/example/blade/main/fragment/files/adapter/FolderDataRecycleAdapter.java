package com.example.blade.main.fragment.files.adapter;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.blade.R;
import com.example.blade.main.fragment.files.model.FileInfo;
import com.example.blade.main.fragment.files.utils.FileUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FolderDataRecycleAdapter extends BaseQuickAdapter<FileInfo, BaseViewHolder> {

    private boolean isPhoto = false;

    public FolderDataRecycleAdapter(@Nullable List<FileInfo> data, boolean isPhoto) {
        super(R.layout.adapter_folder_data_rv_item, data);
        this.isPhoto = isPhoto;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, FileInfo fileInfo) {
        RelativeLayout rlMain = baseViewHolder.getView(R.id.rl_main);
        TextView tv_content = baseViewHolder.getView(R.id.tv_content);
        TextView tv_size = baseViewHolder.getView(R.id.tv_size);
        TextView tv_time = baseViewHolder.getView(R.id.tv_time);
        ImageView iv_cover = baseViewHolder.getView(R.id.iv_cover);
        tv_content.setText(fileInfo.getFileName());
        //tv_size.setText(FileUtil.FormetFileSize(data.get(position).getFileSize()));
        tv_time.setText(fileInfo.getTime());

        //封面图
        if (isPhoto) {
            Glide.with(getContext()).load(fileInfo.getFilePath()).into(iv_cover);
        } else {
            Glide.with(getContext()).load(FileUtil.getFileTypeImageId(getContext(), fileInfo.getFilePath())).fitCenter().into(iv_cover);
        }

    }
}

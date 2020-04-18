package com.example.blade.activity.dump;

import android.widget.ImageView;

import com.blankj.utilcode.util.AppUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.blade.R;

import java.util.List;

import static com.blankj.utilcode.util.FileUtils.getSize;

class ItemApkAdapter extends BaseQuickAdapter<AppUtils.AppInfo, BaseViewHolder> {
    // private ArrayList<ItemApk> alItemApk;
    //private LayoutInflater lInflater;

    ItemApkAdapter(List<AppUtils.AppInfo> data) {
        super(R.layout.item_dump_apk, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppUtils.AppInfo item) {

        helper.setText(R.id.tvApkName, item.getName())
                .setText(R.id.tv_package, item.getPackageName())
                //.setImageDrawable(R.id.ivApkIcon, item.getIcon())
                .setText(R.id.tvApkSize, getSize(String.valueOf(item.getPackagePath())))
                .setText(R.id.tv_path, item.getPackagePath());
        Glide.with(getContext()).load(item.getIcon()).into(helper.<ImageView>getView(R.id.ivApkIcon));
        //helper.setText(R.id.tvApkRunning, item.getRunning() ? "Running" : "");

        //LinearLayout layout = helper.getView(R.id.apkCA);
        //layout.setVisibility(item.isSystem() ? View.VISIBLE : View.GONE);

    }
}





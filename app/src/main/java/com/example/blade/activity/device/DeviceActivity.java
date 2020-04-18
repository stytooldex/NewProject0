package com.example.blade.activity.device;

import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.RomUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.blade.R;
import com.example.blade.base.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;

import static com.blankj.utilcode.util.ClickUtils.applyPressedViewScale;

public class DeviceActivity extends BaseActivity {

    @Keep
    public DeviceActivity() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        RecyclerView r_1 = findViewById(R.id.rec_2);
        //r_1.addItemDecoration(new RVItemDecoration(LinearLayoutCompat.VERTICAL, 24, ContextCompat.getColor(Objects.requireNonNull(getActivity()), R.color.item_activated_color_F), 3));
        r_1.setLayoutManager(new LinearLayoutManager(this));

        BaseQuickAdapter adapter = new BaseQuickAdapter<MultipleItem, BaseViewHolder>(android.R.layout.simple_list_item_activated_2, createSampleData()) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, MultipleItem item) {
                helper.setText(android.R.id.text1, item.getItemType()).setText(android.R.id.text2, item.getSpanSize());
                applyPressedViewScale(helper.getView(android.R.id.text1));
                TextView mTextView = helper.getView(android.R.id.text2);
                mTextView.setTextIsSelectable(true);

            }
        };
        r_1.setAdapter(adapter);
    }

    @Override
    public int setLayoutRes() {
        return R.layout.activity_device;
    }


    private ArrayList<MultipleItem> createSampleData() {
        ArrayList<MultipleItem> items = new ArrayList<>();
        items.add(new MultipleItem("是否 root", DeviceUtils.isDeviceRooted() + ""));
        items.add(new MultipleItem("ADB 是否可用", DeviceUtils.isAdbEnabled() + ""));
        items.add(new MultipleItem("系统版本号", DeviceUtils.getSDKVersionName()));
        items.add(new MultipleItem("系统版本码", DeviceUtils.getSDKVersionCode() + ""));
        items.add(new MultipleItem("AndroidID", DeviceUtils.getAndroidID()));
        items.add(new MultipleItem("MAC 地址", DeviceUtils.getMacAddress()));
        items.add(new MultipleItem("设备厂商", DeviceUtils.getManufacturer()));
        items.add(new MultipleItem("设备型号", DeviceUtils.getModel()));
        items.add(new MultipleItem("支持架构(ABIs)", Arrays.asList(DeviceUtils.getABIs()).toString()));
        items.add(new MultipleItem("是否是平板", DeviceUtils.isTablet() + ""));
        items.add(new MultipleItem("是否是模拟器", DeviceUtils.isEmulator() + ""));
        items.add(new MultipleItem("是否是手机", PhoneUtils.isPhone() + ""));
        items.add(new MultipleItem("唯一设备 ID", DeviceUtils.getUniqueDeviceId("util")));
        items.add(new MultipleItem("是否同一设备", DeviceUtils.getUniqueDeviceId()));
        items.add(new MultipleItem(" ", null));
        items.add(new MultipleItem("设备码", PhoneUtils.getDeviceId()));
        //items.add(new MultipleItem("序列号", PhoneUtils.getSerial()));
        items.add(new MultipleItem("串号(iMEI)", PhoneUtils.getIMEI()));
        items.add(new MultipleItem("MEid 码", PhoneUtils.getMEID()));
        // items.add(new MultipleItem("IMSi 码", PhoneUtils.getIMSI()));
        items.add(new MultipleItem("移动终端类型", PhoneUtils.getPhoneType() + ""));
        items.add(new MultipleItem("ROM 信息", RomUtils.getRomInfo().toString()));
        return items;
    }
}

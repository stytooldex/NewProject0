package com.example.blade.main.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.blade.R;
import com.example.blade.base.BaseRecyclerFragment;
import com.example.blade.main.fragment.dummy.Daily;
import com.example.blade.main.fragment.management.Daily1;
import com.example.blade.util.permission.Permits;

import java.util.ArrayList;

import static com.blankj.utilcode.constant.PermissionConstants.STORAGE;
import static com.blankj.utilcode.util.ClickUtils.applyPressedViewScale;
import static com.example.blade.main.fragment.management.Daily1.items;

public class dailyFragment extends BaseRecyclerFragment {


    @Override
    protected ArrayList<Daily> data() {
        ArrayList<Daily> arrayList = new ArrayList<>();
        arrayList.add(new Daily(items[0], "支持提取图标"));
        arrayList.add(new Daily(items[1], "content"));
        arrayList.add(new Daily(items[2], "content"));
        arrayList.add(new Daily(items[3], "content"));
        arrayList.add(new Daily(items[4], "content"));
        arrayList.add(new Daily(items[5], "content"));
        arrayList.add(new Daily(items[6], "content"));
        //arrayList.add(new Daily(items[7], "content"));
        arrayList.add(new Daily(items[8], "content"));
        return arrayList;
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        applyPressedViewScale(view);
        String string = view.<TextView>findViewById(R.id.item_number).getText().toString();
        Permits.iniQ(requireActivity(), () -> Daily1.INSTANCE.execute(requireActivity(), string), STORAGE);
    }
}

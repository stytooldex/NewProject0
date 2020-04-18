package com.example.blade.main.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.blade.R;
import com.example.blade.base.BaseRecyclerFragment;
import com.example.blade.main.fragment.dummy.Daily;
import com.example.blade.main.fragment.management.Pic;
import com.example.blade.util.permission.Permits;

import java.util.ArrayList;

import static com.blankj.utilcode.constant.PermissionConstants.STORAGE;
import static com.blankj.utilcode.util.ClickUtils.applyPressedViewScale;
import static com.example.blade.main.fragment.management.Pic.Pic_items;

public class PicFragment extends BaseRecyclerFragment {


    @Override
    protected ArrayList<Daily> data() {
        ArrayList<Daily> arrayList = new ArrayList<>();
        arrayList.add(new Daily(Pic_items[0], "content"));
        arrayList.add(new Daily(Pic_items[1], "content"));
        arrayList.add(new Daily(Pic_items[2], "content"));
        arrayList.add(new Daily(Pic_items[3], "content"));

        return arrayList;
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        applyPressedViewScale(view);
        String string = view.<TextView>findViewById(R.id.item_number).getText().toString();
        Permits.iniQ(requireActivity(), () -> Pic.INSTANCE.execute(requireActivity(), string), STORAGE);
    }
}

package com.example.blade.main.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.blade.R;
import com.example.blade.base.BaseRecyclerFragment;
import com.example.blade.main.fragment.dummy.Daily;
import com.example.blade.main.fragment.management.QQ;
import com.example.blade.util.permission.Permits;

import java.util.ArrayList;

import static com.blankj.utilcode.constant.PermissionConstants.STORAGE;
import static com.blankj.utilcode.util.ClickUtils.applyPressedViewScale;
import static com.example.blade.main.fragment.management.QQ.QQ_items;

public class QQFragment extends BaseRecyclerFragment {

    @Override
    protected ArrayList<Daily> data() {
        ArrayList<Daily> arrayList = new ArrayList<>();
        arrayList.add(new Daily(QQ_items[0], "Top"));
        arrayList.add(new Daily(QQ_items[1], "Top"));
        arrayList.add(new Daily(QQ_items[2], "大爱乔碧萝?"));
        arrayList.add(new Daily(QQ_items[3], "Top"));
        return arrayList;
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        applyPressedViewScale(view);
        String string = view.<TextView>findViewById(R.id.item_number).getText().toString();
        Permits.iniQ(requireActivity(), () -> QQ.INSTANCE.execute(requireActivity(), string), STORAGE);
    }
}

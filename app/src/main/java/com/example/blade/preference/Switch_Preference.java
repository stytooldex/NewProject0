package com.example.blade.preference;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bitvale.switcher.SwitcherX;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.blade.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Switch_Preference {
    public Switch_Preference(FragmentActivity activity, RecyclerView recyclerView, List<Kill> list, OnIf onIf) {
        BaseQuickAdapter baseQuickAdapter = new BaseQuickAdapter<Kill, BaseViewHolder>(R.layout.preference_widget_switch_compat_x, list) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, Kill kill) {
                baseViewHolder.setText(R.id.textView_sp, kill.getTitle());
                SwitcherX switcherX = baseViewHolder.getView(R.id.switchWidget);
                switcherX.setOnCheckedChangeListener(i -> {
                    onIf.face(i, kill.getKey());
                    SPUtils.getInstance().put(kill.getKey(), i);
                    return null;
                });
                switcherX.setChecked(SPUtils.getInstance().getBoolean(kill.getKey(), false), true);
            }
        };
        recyclerView.setAdapter(baseQuickAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }

    public interface OnIf {
        void face(boolean i, String s);
    }

    private static String[] Items = {"全屏沉浸", "沉浸状态栏", "沉浸导航栏"};

    public static List<Kill> killList() {
        List<Kill> list = new ArrayList<>();
        list.add(new Kill(Items[0], "1", "3"));
        list.add(new Kill(Items[1], "2", "3"));
        list.add(new Kill(Items[2], "3", "3"));

        return list;
    }


}

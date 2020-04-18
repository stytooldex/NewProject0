package com.example.blade.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.blade.R;
import com.example.blade.main.fragment.dummy.Daily;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.blankj.utilcode.util.ClickUtils.applyPressedViewScale;

public abstract class BaseRecyclerFragment extends BaseFLazyFragment implements OnItemClickListener {


    @Override
    public int setLayoutRes() {
        return R.layout.fragment_universal_list;
    }

    @Override
    public void initView(View view) {

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView2);
        final int columnCount = 2;

        BaseQuickAdapter baseQuickAdapter = new BaseQuickAdapter<Daily, BaseViewHolder>(R.layout.fragment_daily, data()) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, Daily s) {
                baseViewHolder
                        .setText(R.id.item_number, s.getId())
                        .setText(R.id.content, s.getContent());

            }
        };
        recyclerView.setAdapter(baseQuickAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), columnCount));
        baseQuickAdapter.setOnItemClickListener(this);
        baseQuickAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                applyPressedViewScale(view);
                return false;
            }
        });

    }

    protected abstract ArrayList<Daily> data();


}

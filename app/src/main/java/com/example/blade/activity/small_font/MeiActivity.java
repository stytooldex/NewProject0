package com.example.blade.activity.small_font;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.text.SpannableString;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.blade.R;
import com.example.blade.base.BaseActivity;

import java.util.Arrays;


public class MeiActivity extends BaseActivity {

    private EditText editText;
    private String[] VIEW_ITEM = {"#这是在上面的小字#", "¹", "²", "³", "⁴", "⁵", "⁶", "⁷", "⁸", "⁹", "º", "#这是在下面的小字#", "₁", "₂", "₃", "₄", "₅", "₆", "₇", "₈", "₉", "₀", "#这是蓝字#", "🇦", "🇧", "🇨", "🇩", "🇪", "🇫", "🇬", "🇭", "🇮", "🇯", "🇰", "🇱", "🇲", "🇳", "🇴", "🇵", "🇶", "🇷", "🇸", "🇹", "🇺", "🇻", "🇼", "🇽", "🇾", "🇿"};

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        editText = findViewById(R.id.mainEditText10);
        //ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, ContantValue.VIEW_ITEM);
        RecyclerView mRecyclerView = findViewById(R.id.api_id_views_listview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        BaseQuickAdapter mQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_activated_1, Arrays.asList(VIEW_ITEM)) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, String item) {
                TextView tvContent = helper.getView(android.R.id.text1);
                tvContent.setText(item);
            }
        };
        // mQuickAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(mQuickAdapter);
        mQuickAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                TextView a = view.findViewById(android.R.id.text1);
                startIntent(a.getText().toString().trim());
            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.me_main;
    }


    private void startIntent(String classes) {
        SpannableString spannableString = new SpannableString(classes);
        int curds = editText.getSelectionStart();
        editText.getText().insert(curds, spannableString);
    }

    public void mama(View view) {
        ToastUtils.showLong("复制成功");
        ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (manager != null) {
            manager.setPrimaryClip(ClipData.newPlainText("Label", editText.getText().toString().trim()));
        }
    }
}


package com.example.blade.activity.tools;

import com.blankj.utilcode.util.ToastUtils;
import com.example.blade.R;
import com.example.blade.activity.All.BlankFragment2;
import com.example.blade.base.BaseActivity;

import static com.example.blade.ReaderUtil.replaceFragment;

public class tools_Activity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String m2 = getIntent().getStringExtra("AEO");

        if (m2 != null) {
            switch (m2) {
                case "QQ处理工具":
                  /*  Permission.permission(this, new Permission.Callback() {
                        @Override
                        public void ok() {
                            replaceFragment(getSupportFragmentManager(), R.id.AEP_tools, new PreferenceTestFragment());
                        }
                    }, STORAGE);*/

                    break;
                case "全部网址功能":
                    replaceFragment(getSupportFragmentManager(), R.id.AEP_tools, new BlankFragment2());
                    break;
                case "电池伪装":
                case "过渡动画修改":
                    ToastUtils.showLong("暂未实现");
                    finish();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_tools_;
    }
}

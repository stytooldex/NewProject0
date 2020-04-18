package com.example.blade.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author 22844
 */
public abstract class BaseFLazyFragment extends Fragment {

    private View rootView;
    private boolean needInitData = true;
    private boolean isInitView = false;

    public BaseFLazyFragment() {
    }

    public abstract @LayoutRes
    int setLayoutRes();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            isInitView = true;
            rootView = inflater.inflate(setLayoutRes(), container, false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        lazyLoad();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    private void lazyLoad() {
        if (getUserVisibleHint() && needInitData && isInitView) {
            needInitData = false;
            if (rootView != null) {
                initView(rootView);
            }
        }
    }

    public abstract void initView(View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
        rootView = null;
    }


}
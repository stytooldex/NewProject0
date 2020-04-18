package com.example.blade.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.blade.R;

import java.util.ArrayList;

/**
 * A [FragmentPagerAdapter] that returns a com.example.blade.main.fragment corresponding to
 * one of the sections/tabs/pages.
 *
 * @author Nico
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_system, R.string.tab_text_qq, R.string.tab_text_mg, R.string.tab_text_file0};
    private final Context mContext;
    private final ArrayList<Fragment> list;

    public SectionsPagerAdapter(Context context, FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the com.example.blade.main.fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return list.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
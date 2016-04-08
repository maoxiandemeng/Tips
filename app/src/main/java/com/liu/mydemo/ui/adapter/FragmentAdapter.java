package com.liu.mydemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by liu on 2016/1/4.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter{
    private ArrayList<Fragment> fragments;
    private ArrayList<String> list;

    public FragmentAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments, ArrayList<String> list) {
        super(fragmentManager);
        this.fragments = fragments;
        this.list = list;
    }

    /**
     * 可以动态添加一个Tab
     * @param s
     * @param fragment
     */
    public void addItemFragment(String s, Fragment fragment){
        list.add(s);
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.size() == 0 ? null : fragments.get(position) ;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}

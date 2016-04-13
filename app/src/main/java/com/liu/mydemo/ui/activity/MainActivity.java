package com.liu.mydemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.liu.mydemo.R;
import com.liu.mydemo.presenter.MainPresenter;
import com.liu.mydemo.ui.adapter.FragmentAdapter;
import com.liu.mydemo.ui.base.BaseCompatActivity;
import com.liu.mydemo.ui.fragment.FunnyFragment;
import com.liu.mydemo.ui.fragment.HotFragment;
import com.liu.mydemo.ui.fragment.MusicFragment;
import com.liu.mydemo.utils.StatusBarUtil;
import com.liu.mydemo.view.MainView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseCompatActivity implements NavigationView.OnNavigationItemSelectedListener,MainView{
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.tool_bar)
    Toolbar mToolbar;
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPage;

    private MainPresenter mainPresenter;
    private FragmentAdapter mFragmentAdapter;

    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, R.color.colorPrimaryDark);
        mNavView.setNavigationItemSelectedListener(this);

        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadData();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.snow:
                startActivity(new Intent(MainActivity.this, SnowActivity.class));
                break;
            case R.id.scan:
                startActivity(new Intent(MainActivity.this, ScanActivity.class));
                break;
            case R.id.dialog:
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
                break;
            case R.id.chart:
                startActivity(new Intent(MainActivity.this, ChartActivity.class));
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void showData() {
        testData();
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments, mList);
        mViewPage.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPage);
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);

//        mFragmentAdapter.addItemFragment("游戏", new HotFragment());
//        mTabLayout.addTab(mTabLayout.newTab());
//        mFragmentAdapter.notifyDataSetChanged();
//        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);
    }

    private void testData(){
        mFragments = new ArrayList<>();
        mList = new ArrayList<>();

        mList.add("热门");
        mFragments.add(new HotFragment());
        mList.add("搞笑");
        mFragments.add(new FunnyFragment());
        mList.add("音乐");
        mFragments.add(new MusicFragment());
    }
}

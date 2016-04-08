package com.liu.mydemo.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liu.mydemo.R;
import com.liu.mydemo.presenter.HotPresenter;
import com.liu.mydemo.ui.adapter.HotAdapter;
import com.liu.mydemo.ui.base.BaseFragment;
import com.liu.mydemo.view.HotView;
import com.liu.refresh.OnLoadMoreListener;
import com.liu.refresh.OnRefreshListener;
import com.liu.refresh.SwipeRefreshToLoadLayout;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by liu on 2016/1/4.
 */
public class HotFragment extends BaseFragment implements HotView, OnRefreshListener, OnLoadMoreListener{
    @Bind(R.id.swipeRefreshToLoadLayout)
    SwipeRefreshToLoadLayout mSwipeRefreshToLoadLayout;
    @Bind(R.id.swipe_target)
    RecyclerView mRecyclerView;

    private HotPresenter mHotPresenter;
    private HotAdapter mHotAdapter;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_hot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHotPresenter = new HotPresenter();
        mHotPresenter.attachView(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHotAdapter = new HotAdapter(getActivity(), list);
        mRecyclerView.setAdapter(mHotAdapter);

        mSwipeRefreshToLoadLayout.setOnRefreshListener(this);
        mSwipeRefreshToLoadLayout.setOnLoadMoreListener(this);
        mSwipeRefreshToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshToLoadLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void showData() {
        for (int i = 0; i < 20; i++) {
            list.add("Refresh" + i);
        }
        mHotAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        if (mHotAdapter.getItemCount() != 0) list.clear();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mHotPresenter.loadData();
                mSwipeRefreshToLoadLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
//        for (int i = 0; i < 10; i++) {
//            list.add("LoadMore" + i);
//        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mHotPresenter.loadData();
//                mHotAdapter.notifyDataSetChanged();
                mSwipeRefreshToLoadLayout.setLoadingMore(false);
            }
        }, 2000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHotPresenter.detachView();
    }
}

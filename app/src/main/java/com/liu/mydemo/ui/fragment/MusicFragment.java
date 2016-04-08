package com.liu.mydemo.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liu.mydemo.R;
import com.liu.mydemo.presenter.HotPresenter;
import com.liu.mydemo.ui.adapter.HotAdapter;
import com.liu.mydemo.ui.adapter.MusicAdapter;
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
public class MusicFragment extends BaseFragment implements HotView, OnRefreshListener, OnLoadMoreListener{
    @Bind(R.id.swipeRefreshToLoadLayout)
    SwipeRefreshToLoadLayout mSwipeRefreshToLoadLayout;
    @Bind(R.id.swipe_target)
    RecyclerView mRecyclerView;

    private HotPresenter mHotPresenter;
    private MusicAdapter mMusicAdapter;
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
        mMusicAdapter = new MusicAdapter(getActivity(), list);
        mRecyclerView.setAdapter(mMusicAdapter);

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
        mMusicAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        if (mMusicAdapter.getItemCount() != 0) list.clear();

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                mHotPresenter.loadData();
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

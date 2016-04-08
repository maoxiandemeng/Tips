package com.liu.mydemo.presenter;

import com.liu.mydemo.view.HotView;

/**
 * Created by liu on 2016/2/24.
 */
public class HotPresenter implements Presenter<HotView> {
    private HotView hotView;

    @Override
    public void attachView(HotView view) {
        this.hotView = view;
    }

    @Override
    public void detachView() {
        this.hotView = null;
    }

    public void loadData() {
        if (hotView != null) {
            hotView.showData();
        }
    }
}

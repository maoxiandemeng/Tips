package com.liu.mydemo.presenter;

import com.liu.mydemo.view.MainView;

/**
 * Created by liu on 2016/1/4.
 */
public class MainPresenter implements Presenter<MainView> {
    private MainView mainView;

    @Override
    public void attachView(MainView view) {
        this.mainView = view;
    }

    @Override
    public void detachView() {
        this.mainView = null;
    }

    /**
     * 处理和请求服务器的接口数据
     */
    public void loadData(){
        mainView.showData();
    }
}

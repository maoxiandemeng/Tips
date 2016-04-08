package com.liu.mydemo.presenter;

/**
 * Created by liu on 2016/1/4.
 */
public interface Presenter<V> {
    void attachView(V view);
    void detachView();
}

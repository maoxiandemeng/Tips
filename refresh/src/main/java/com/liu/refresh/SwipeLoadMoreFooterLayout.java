package com.liu.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 *
 */
public class SwipeLoadMoreFooterLayout extends FrameLayout implements  SwipeTrigger,SwipeLoadMoreTrigger{

    public SwipeLoadMoreFooterLayout(Context context) {
        super(context);
    }

    public SwipeLoadMoreFooterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeLoadMoreFooterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onSwipe(int y, boolean isComplete) {

    }

    @Override
    public void onRelease() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onReset() {

    }
}

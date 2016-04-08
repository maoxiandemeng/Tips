package com.liu.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 *
 */
public class SwipeRefreshHeaderLayout extends FrameLayout implements SwipeTrigger,SwipeRefreshTrigger {

    public SwipeRefreshHeaderLayout(Context context) {
        super(context);
    }

    public SwipeRefreshHeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeRefreshHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onRefresh() {

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

package com.liu.refresh;

/**
 *
 */
public interface SwipeTrigger {
    void onPrepare();

    void onSwipe(int y, boolean isComplete);

    void onRelease();

    void complete();

    void onReset();
}

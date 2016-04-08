package com.liu.mydemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2016/3/17.
 * 经纬度的轨迹
 * 经度   longitude X轴
 * 纬度   latitude Y轴
 */
public class PathView extends View{
    private final String TAG = this.getClass().getName();
    //最多画六个点
    private static final int COUNT = 6;

    private List<PointF> points;
    private float minX  = 0, maxX = 0;
    private float minY = 0, maxY = 0;
    private Paint paint = new Paint();
    private int height;
    private int width;
    private Paint mPaint;

    public PathView(Context context) {
        this(context, null, 0);
    }

    public PathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        mPaint = new Paint();
//        mPaint.setColor(Color.GREEN);
//        mPaint.setAntiAlias(true);
//        mPaint.setTextSize(20f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged");
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw");
//        canvas.drawLine(50, 0, 50, height, mPaint);
//        canvas.drawLine(50, height, width, height, mPaint);
//        drawX(canvas);
//        drawY(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        for (int i = 0; i < points.size(); i++) {
            PointF point = getNewPoint(points.get(i));
            paint.setColor(Color.BLUE);
            canvas.drawCircle(point.x, point.y, 3, paint);
            if (i > 0 && i <= points.size() - 1){
                PointF prePoint = getNewPoint(points.get(i - 1));
                paint.setColor(i != points.size() - 1 ? Color.GRAY : Color.RED);
                canvas.drawLine(prePoint.x, prePoint.y, point.x, point.y, paint);
            }
        }

    }

    private PointF getNewPoint(PointF point){
        return new PointF(getNewX(point.x), getNewY(point.y));
    }

    private float getNewX(float x){
       return  (width/(maxX - minX))*(x - minX);
    }

    private float getNewY(float y){
        float newY =  height/(maxY - minY)*(y - minY);
        return height - newY;
    }

    public void setPoints(ArrayList<PointF> list){
        if (list == null || list.isEmpty()) return;
        if (list.size() <= COUNT) {
            points = list;
        }else {
            points = list.subList(list.size() - COUNT, list.size());
        }

        for (PointF point : points) {
            minX = Math.min(minX, point.x);
            maxX = Math.max(maxX, point.x);
            minY = Math.min(minY, point.y);
            maxY = Math.max(maxY, point.y);
        }
        Log.i(TAG, "X:" + minX + " ; " + maxX);
        Log.i(TAG, "Y:" + minY + " ; " + maxY);
        invalidate();
    }

    private void drawX(Canvas canvas){
        int textWidth = toRect(String.valueOf(minX)).width();

        canvas.drawText(String.valueOf(minX), 0, height, mPaint);
        canvas.drawText(String.valueOf(maxX), width - textWidth, height, mPaint);
    }

    private void drawY(Canvas canvas){
        int textHeight = toRect(String.valueOf(minY)).height();

        canvas.drawText(String.valueOf(minY), 0, height - textHeight, mPaint);
        canvas.drawText(String.valueOf((maxY - minY)/2), 0, height/2, mPaint);
        canvas.drawText(String.valueOf(maxY), 0, textHeight * 2, mPaint);
    }

    private Rect toRect(String s){
        Rect rect = new Rect();
        mPaint.getTextBounds(s, 0, s.length(), rect);
        return rect;
    }

    public float getMinX() {
        return minX;
    }

    public void setMinX(float minX) {
        this.minX = minX;
    }

    public float getMaxX() {
        return maxX;
    }

    public void setMaxX(float maxX) {
        this.maxX = maxX;
    }

    public float getMinY() {
        return minY;
    }

    public void setMinY(float minY) {
        this.minY = minY;
    }

    public float getMaxY() {
        return maxY;
    }

    public void setMaxY(float maxY) {
        this.maxY = maxY;
    }
}

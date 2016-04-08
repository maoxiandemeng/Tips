package com.liu.mydemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.liu.mydemo.R;

/**
 * Created by liu on 2016/3/21.
 */
public class AngularSpeedView extends View {
    private final String TAG = this.getClass().getName();
    private int width, height;
    private float averageX, averageY;
    private int right = 0;
    private int left = 1;
    private int type;
    private int color;
    private float speed;
    private Paint defaultPaint;
    private Paint showPaint;

    public AngularSpeedView(Context context) {
        this(context, null, 0);
    }

    public AngularSpeedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AngularSpeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AngularSpeedView, defStyleAttr, 0);
        type = array.getInt(R.styleable.AngularSpeedView_type, right);
        color = array.getColor(R.styleable.AngularSpeedView_show_color, Color.BLUE);

        defaultPaint = new Paint();
        defaultPaint.setAntiAlias(true);
        defaultPaint.setStyle(Paint.Style.FILL);
        defaultPaint.setColor(Color.WHITE);

        showPaint = new Paint();
        showPaint.setAntiAlias(true);
        showPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        averageX = width/29;
        averageY = height/30;
        Log.i(TAG, "width: " + width + "height: " + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw");
        drawDefaultWhite(canvas);
        drawSpeed(canvas);
    }

    private void drawDefaultWhite(Canvas canvas) {
        RectF rectF = null;

        for (int i = 1; i < 15; i++) {
            if (i != 5 && i != 10) {
                if (type == right) {
                    rectF = new RectF(averageX * (2 * i - 1), height / 2 - averageY * i, averageX * (2 * i), height / 2 + averageY * i);
                }else if (type == left) {
                     rectF = new RectF(width - averageX * (2 * i), height / 2 - averageY * i, width - averageX * (2 * i - 1), height / 2 + averageY * i);
                }
                canvas.drawRect(rectF, defaultPaint);
            }
        }
    }

    private void drawSpeed(Canvas canvas){
        showPaint.setColor(color);
        if (speed <= 0) return;
        int min;
        int max;
        if (speed > 0 && speed <= 0.1){
            min = 0;
            max = 10;
            drawCommon(canvas, min, max, speed*100, 0);
        }else if (speed > 0.1 && speed <= 1){
            for (int i = 1; i < 5; i++) {
                    RectF rectF = null;
                    if (type == right) {
                        rectF = new RectF(averageX * (2 * i - 1), height / 2 - averageY * i, averageX * (2 * i), height / 2 + averageY * i);
                    }else if (type == left) {
                        rectF = new RectF(width - averageX * (2 * i), height / 2 - averageY * i, width - averageX * (2 * i - 1), height / 2 + averageY * i);
                    }
                    canvas.drawRect(rectF, showPaint);
            }
            min = 1;
            max = 10;
            drawCommon(canvas, min, max, speed * 10, 5);
        }if (speed > 1 && speed <= 9){
            for (int i = 1; i < 10; i++) {
                if (i != 5) {
                    RectF rectF = null;
                    if (type == right) {
                        rectF = new RectF(averageX * (2 * i - 1), height / 2 - averageY * i, averageX * (2 * i), height / 2 + averageY * i);
                    } else if (type == left) {
                        rectF = new RectF(width - averageX * (2 * i), height / 2 - averageY * i, width - averageX * (2 * i - 1), height / 2 + averageY * i);
                    }
                    canvas.drawRect(rectF, showPaint);
                }
            }
            min = 1;
            max = 9;
            drawCommon(canvas, min, max, speed, 10);
        }
//        RectF rectF = null;
        //1~9
//        speed = speed - 1;
//        if (speed <= 0) return;
//        int count = (int) (4*speed/8);
//        if (count != 0) {
//            for (int i = 1; i < count + 1; i++) {
//                if (type == right) {
//                    rectF = new RectF(averageX * (2 * i - 1), height / 2 - averageY * i, averageX * (2 * i), height / 2 + averageY * i);
//                } else if (type == left) {
//                    rectF = new RectF(width - averageX * (2 * i), height / 2 - averageY * i, width - averageX * (2 * i - 1), height / 2 + averageY * i);
//                }
//                canvas.drawRect(rectF, showPaint);
//            }
//        }
//        float offsetX = averageX * (4 * speed / 8 - count);
//        if (offsetX == 0f) return;
//        int x = count + 1;
//        if (type == right) {
//            rectF = new RectF(averageX * (2 * x - 1), height / 2 - averageY * x, averageX * (2 * x - 1) + offsetX, height / 2 + averageY * x);
//        } else if (type == left){
//            rectF = new RectF(width - averageX * (2 * x - 1) - offsetX, height / 2 - averageY * x, width - averageX * (2 * x - 1), height / 2 + averageY * x);
//        }
//        canvas.drawRect(rectF, showPaint);
    }

    /**
     *
     * @param canvas
     */
    private void drawMin(Canvas canvas){
//        if (speed <= 0) return;
//        int min = 0;
//        int max = 0;
//        if (speed > 0 && speed <= 0.1){
//            min = 0;
//            max = 10;
//            drawCommon(canvas, min, max, speed*100);
//        }else if (speed > 0.1 && speed <= 1){
//            for (int i = 1; i < count + 1; i++) {
//                if (type == right) {
//                    rectF = new RectF(averageX * (2 * i - 1), height / 2 - averageY * i, averageX * (2 * i), height / 2 + averageY * i);
//                } else if (type == left) {
//                    rectF = new RectF(width - averageX * (2 * i), height / 2 - averageY * i, width - averageX * (2 * i - 1), height / 2 + averageY * i);
//                }
//                canvas.drawRect(rectF, showPaint);
//            }
//        }if (speed > 1 && speed <= 9){
//            min = 1;
//            max = 9;
//            speed = speed - min;
//        }
//        if (speed <= 0) return;
//        int count = (int) (4*speed/8);
//        if (count != 0) {
//            for (int i = 1; i < count + 1; i++) {
//                if (type == right) {
//                    rectF = new RectF(averageX * (2 * i - 1), height / 2 - averageY * i, averageX * (2 * i), height / 2 + averageY * i);
//                } else if (type == left) {
//                    rectF = new RectF(width - averageX * (2 * i), height / 2 - averageY * i, width - averageX * (2 * i - 1), height / 2 + averageY * i);
//                }
//                canvas.drawRect(rectF, showPaint);
//            }
//        }
//        float offsetX = averageX * (4 * speed / 8 - count);
//        if (offsetX == 0f) return;
//        int x = count + 1;
//        if (type == right) {
//            rectF = new RectF(averageX * (2 * x - 1), height / 2 - averageY * x, averageX * (2 * x - 1) + offsetX, height / 2 + averageY * x);
//        } else if (type == left){
//            rectF = new RectF(width - averageX * (2 * x - 1) - offsetX, height / 2 - averageY * x, width - averageX * (2 * x - 1), height / 2 + averageY * x);
//        }
//        canvas.drawRect(rectF, showPaint);
    }

    private void drawCommon(Canvas canvas, int min, int max, float speed, int start){
        speed = speed - min;
        if (speed <= 0) return;
        RectF rectF = null;
        int sub = max - min;
        int count = (int) (4*speed/sub);
        if (count != 0) {
            for (int i = 1 + start; i < count + 1 + start; i++) {
                if (type == right) {
                    rectF = new RectF(averageX * (2 * i - 1), height / 2 - averageY * i, averageX * (2 * i), height / 2 + averageY * i);
                } else if (type == left) {
                    rectF = new RectF(width - averageX * (2 * i), height / 2 - averageY * i, width - averageX * (2 * i - 1), height / 2 + averageY * i);
                }
                canvas.drawRect(rectF, showPaint);
            }
        }
        float offsetX = averageX * (4 * speed / sub - count);
        if (offsetX == 0f) return;
        int x = count + 1 + start;
        if (type == right) {
            rectF = new RectF(averageX * (2 * x - 1), height / 2 - averageY * x, averageX * (2 * x - 1) + offsetX, height / 2 + averageY * x);
        } else if (type == left){
            rectF = new RectF(width - averageX * (2 * x - 1) - offsetX, height / 2 - averageY * x, width - averageX * (2 * x - 1), height / 2 + averageY * x);
        }
        canvas.drawRect(rectF, showPaint);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
        invalidate();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

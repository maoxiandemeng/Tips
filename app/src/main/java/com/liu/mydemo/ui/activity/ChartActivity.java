package com.liu.mydemo.ui.activity;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.liu.mydemo.R;
import com.liu.mydemo.widget.AngularSpeedView;
import com.liu.mydemo.widget.PathView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChartActivity extends AppCompatActivity implements OnChartValueSelectedListener{
//    @Bind(R.id.lineChart)
//    LineChart mChart;
    @Bind(R.id.pathView)
    PathView pathView;

    @Bind(R.id.leftSpeed)
    AngularSpeedView leftSpeed;
    @Bind(R.id.rightSpeed)
    AngularSpeedView rightSpeed;

    ArrayList<PointF> list = new ArrayList<>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            list.add(new PointF(new Random().nextFloat() * 150, new Random().nextFloat() * 150));
//            pathView.setPoints(list);
//            int i = new Random().nextInt(9) + 1;
//            rightSpeed.setSpeed(0.55f);
            leftSpeed.setSpeed(0.05f);
            rightSpeed.setSpeed(0.55f);
//            handler.sendEmptyMessageDelayed(1, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);

        list.add(new PointF(1, 6));
        list.add(new PointF(5, 16));
        list.add(new PointF(-1, 12));
        list.add(new PointF(-4, 4));
        list.add(new PointF(2, 14));
        list.add(new PointF(1, -4));
        list.add(new PointF(20, 20));
        list.add(new PointF(12, 17));
        pathView.setPoints(list);

        leftSpeed.setSpeed(2f);
        rightSpeed.setSpeed(0.05f);

        handler.sendEmptyMessageDelayed(1, 5000);

//        mChart.setOnChartValueSelectedListener(this);
        // no description text
//        mChart.setDescription("");
        //no data
//        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable touch gestures
//        mChart.setTouchEnabled(true);

//        mChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
//        mChart.setDragEnabled(true);
//        mChart.setScaleEnabled(true);
//        mChart.setDrawGridBackground(false);
//        mChart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
//        mChart.setPinchZoom(true);

        // set an alternative background color
//        mChart.setBackgroundColor(Color.LTGRAY);
//        setData(10);
//        mChart.setRendererRightYAxis(null);

        // get the legend (only possible after setting data)
//        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
//        l.setForm(Legend.LegendForm.LINE);
//        l.setTextSize(11f);
//        l.setTextColor(Color.WHITE);
//        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

//        XAxis xAxis = mChart.getXAxis();
//        xAxis.setTextSize(12f);
//        xAxis.setTextColor(Color.WHITE);
//        xAxis.setDrawGridLines(false);
//        xAxis.setDrawAxisLine(false);
//        xAxis.setSpaceBetweenLabels(1);
//
//        YAxis leftAxis = mChart.getAxisLeft();
//        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
//        leftAxis.setAxisMaxValue(150f);
//        leftAxis.setAxisMinValue(0f);
//        leftAxis.setDrawGridLines(true);
    }

    private void setData(int count) {
        ArrayList<String> xVals = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals1 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * 10) + 50;// + (float)
            // ((mult *
            // 0.1) / 10);
            yVals1.add(new Entry(val, i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals1, "DataSet 1");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setCircleColor(Color.WHITE);
        set1.setLineWidth(2f);
        set1.setCircleRadius(3f);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);
        //set1.setFillFormatter(new MyFillFormatter(0f));
//        set1.setDrawHorizontalHighlightIndicator(false);
//        set1.setVisible(false);
//        set1.setCircleHoleColor(Color.WHITE);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
//        LineData data = new LineData(xVals, dataSets);
//        data.setValueTextColor(Color.WHITE);
//        data.setValueTextSize(9f);

        // set data
//        mChart.setData(data);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());

//        mChart.centerViewToAnimated(e.getXIndex(), e.getVal(), mChart.getData().getDataSetByIndex(dataSetIndex).getAxisDependency(), 500);
    }

    @Override
    public void onNothingSelected() {

    }

    @OnClick(R.id.fangda)
    public void fangda(){
        pathView.setMinX(pathView.getMinX() + 5);
        pathView.setMaxX(pathView.getMaxX() - 5);
        pathView.setMinY(pathView.getMinY() + 5);
        pathView.setMaxY(pathView.getMaxY() - 5);
        pathView.invalidate();
    }

    @OnClick(R.id.suoxiao)
    public void suoxiao(){
        pathView.setMinX(pathView.getMinX() - 5);
        pathView.setMaxX(pathView.getMaxX() + 5);
        pathView.setMinY(pathView.getMinY() - 5);
        pathView.setMaxY(pathView.getMaxY() + 5);
        pathView.invalidate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }
}

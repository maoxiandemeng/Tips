package com.liu.mydemo.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.mydemo.R;
import com.liu.mydemo.ui.base.BaseCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import comliu.scanlibrary.CaptureActivity;
import comliu.scanlibrary.encode.Encoder;

public class ScanActivity extends BaseCompatActivity {
    @Bind(R.id.tool_bar)
    Toolbar mToolBar;
    @Bind(R.id.scan_result)
    TextView mResult;
    @Bind(R.id.encode_image)
    ImageView encodeImg;

    private Encoder mEncoder;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap = (Bitmap) msg.obj;
            encodeImg.setImageBitmap(bitmap);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
        mToolBar.setTitle("二维码");
        setSupportActionBar(mToolBar);
        // Navigation Icon 要設定在 setSupoortActionBar 才有作用
        // 否則會出現 back button
        mToolBar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mEncoder = new Encoder.Builder()
                .setBackgroundColor(0xFFFFFF)
                .setCodeColor(0xFF000000)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = mEncoder.encode("123456");
                handler.sendMessage(handler.obtainMessage(0, bitmap));
            }
        }).start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.scan:
                startActivityForResult(new Intent(ScanActivity.this, CaptureActivity.class), CaptureActivity.SCAN_CODE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == CaptureActivity.SCAN_CODE){
                String result = data.getStringExtra(CaptureActivity.SCAN_RESULT);
                mResult.setText("扫描结果" + result);
            }
        }
    }
}

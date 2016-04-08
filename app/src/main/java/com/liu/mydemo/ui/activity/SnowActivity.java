package com.liu.mydemo.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.liu.mydemo.R;
import com.liu.mydemo.ui.base.BaseCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SnowActivity extends BaseCompatActivity {
    @Bind(R.id.tool_bar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow);
        ButterKnife.bind(this);
        mToolbar.setTitle("雪花");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

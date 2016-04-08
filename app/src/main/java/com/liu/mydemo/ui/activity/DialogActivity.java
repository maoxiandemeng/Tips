package com.liu.mydemo.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.dialog.MaterialDialog;
import com.liu.mydemo.R;
import com.liu.mydemo.ui.base.BaseCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends BaseCompatActivity {
    @Bind(R.id.tool_bar)
    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
        mToolBar.setTitle("Dialog");
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.dialog_default)
    void defaultDialog(){
        final MaterialDialog dialog = new MaterialDialog(this);
        dialog.setTitle("MaterialDialog").setMessage("嗨！这是一个 MaterialDialog. 它非常方便使用，你只需将它实例化，这个美观的对话框便会自动地显示出来。它简洁小巧，完全遵照 Google 2014 年发布的 Material Design 风格，希望你能喜欢它！")
                .setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(DialogActivity.this, "Ok",
                                Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(DialogActivity.this, "Cancel",
                        Toast.LENGTH_LONG).show();
            }
        }).setCanceledOnTouchOutside(false) //Dialog外点击无反应，只能点返回键消失Dialog
//                .setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                Toast.makeText(DialogActivity.this, "onDismiss",
//                        Toast.LENGTH_LONG).show();
//            }
//        })
            .show();
    }

    @OnClick(R.id.dialog_view)
    void setViewDialog(){
        MaterialDialog dialog = new MaterialDialog(this);
        TextView textView = new TextView(this);
        textView.setText("嗨！这是一个 MaterialDialog.");
        dialog.setBackgroundResource(R.color.google_green);
        dialog.setView(textView).show();
    }

    @OnClick(R.id.dialog_contentView)
    void setContentViewDialog(){
        final MaterialDialog dialog = new MaterialDialog(this);
        TextView textView = new TextView(this);
        textView.setText("嗨！这是一个 MaterialDialog.");
        dialog.setBackgroundResource(R.color.google_green);
        dialog.setContentView(textView);
        dialog.setPositiveButton("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}

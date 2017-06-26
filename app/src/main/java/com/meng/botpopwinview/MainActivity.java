package com.meng.botpopwinview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, BottomPopView.AnimatorListener {

    private BottomPopView bottomPopView;
    private View contentView;
    private LinearLayout mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initView() {
        // 缩放窗体布局
        mainView = (LinearLayout) findViewById(R.id.main_view);

        // popView
        bottomPopView = (BottomPopView) findViewById(R.id.bottom_popup);

        // popView布局
        contentView = LayoutInflater.from(this).inflate(R.layout.layout_content_view, null);
        bottomPopView.setContextView(contentView);

        // 缩放窗体布局动画
        bottomPopView.setAnimatorListener(this);
    }

    private void initListener() {
        // 弹窗切换
        findViewById(R.id.guige).setOnClickListener(this);

        bottomPopView.setOnClickListener(this);
        contentView.findViewById(R.id.ic_cancel).setOnClickListener(this);
        contentView.findViewById(R.id.red).setOnClickListener(this);
        contentView.findViewById(R.id.yellow).setOnClickListener(this);
        contentView.findViewById(R.id.blue).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_cancel:
                bottomPopView.disMissPopupView();
                break;
            case R.id.guige:
                bottomPopView.showPopouView();
                break;
            case R.id.red:
                showText("red");
                break;
            case R.id.yellow:
                showText("yellow");
                break;
            case R.id.blue:
                showText("blue");
                break;
        }
    }

    private void showText(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startValue(int value) {
        setMargins(mainView, value - 10, value, value - 10, value);
    }

    @Override
    public void endValue(int value) {
        setMargins(mainView, value, value, value, value);
    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }


}

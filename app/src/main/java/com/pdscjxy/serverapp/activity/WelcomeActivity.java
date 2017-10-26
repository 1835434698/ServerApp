package com.pdscjxy.serverapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.pdscjxy.serverapp.R;
import com.pdscjxy.serverapp.activity.base.BaseActivity;

/**
 * Created by Administrator on 2017/10/26.
 */

public class WelcomeActivity extends BaseActivity {
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Intent intent = new Intent(WelcomeActivity.this, BannerActivity.class);
            startActivity(intent);
            finish();
        }

    };

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_welcome);
        hideTitle();
        mHandler.sendEmptyMessageDelayed(0, 1500);
    }
}

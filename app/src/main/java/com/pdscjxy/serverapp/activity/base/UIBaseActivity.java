package com.pdscjxy.serverapp.activity.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.pdscjxy.serverapp.R;
import com.pdscjxy.serverapp.util.Logger;

/**
 * Created by Administrator on 2017/10/26.
 */

public class UIBaseActivity extends AppCompatActivity implements IActivity{
    protected boolean isFirstLayout = true;
    private UILayer uiLayer;
    protected UILayer ui;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        uiLayer = new UILayer(this);
        ui = uiLayer;

    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void setContentView(int layoutResID) {
        // TODO Auto-generated method stub
        super.setContentView(R.layout.activity_base);
        if (layoutResID < 0) {
            return;
        }
        setView(layoutResID);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }


    /**
     * 设置Activity的内容布局，取代setContentView（）方法
     */
    public void setView(int layoutResID) {
        LinearLayout content_linear = (LinearLayout) this.findViewById(R.id.content_view);
        content_linear.addView(View.inflate(this, layoutResID, null), new LinearLayout.LayoutParams(-1, -1));
    }


    /**
     * 包内方法，返回内部的UI层对象，类似于ActionBar对象的获取
     * 默认可以使用UIBaseActivity.ui来访问该对象
     * @return 内部UI层的抽象对象
     */
	/*package*/ UILayer getUILayer() {
        return uiLayer;
    }

    @Override
    public void setCustomedLayout(boolean customed) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.i(getClass().getSimpleName(), "onActivityResult：" + getClass().getSimpleName() + "   intent is null： " + (data == null));
        if (!ui.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 当activity第一次layout之后
     */
    @Override
    public void onActivityFirstLayout() {
        isFirstLayout = false;
        Logger.i(getClass().getSimpleName(), "onActivityFirstLayout"+"   isFirstLayout:"+isFirstLayout);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.i(getClass().getSimpleName(), "onResume"+"   isFirstLayout:"+isFirstLayout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.i(getClass().getSimpleName(), "onPause");
    }




}

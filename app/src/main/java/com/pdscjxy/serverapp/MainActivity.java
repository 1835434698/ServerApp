package com.pdscjxy.serverapp;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import com.pdscjxy.serverapp.activity.base.BaseActivity;
import com.pdscjxy.serverapp.manager.Constant;
import com.pdscjxy.serverapp.net.OkHttpManager;
import com.pdscjxy.serverapp.util.Logger;
import com.pdscjxy.serverapp.util.Toasts;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Constant.context = this;

        checkPermission(new CheckPermListener() {
            @Override
            public void superPermission() {
//                login();
            }
        }, R.string.ask_again, Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        final Map<String, String> httpParams =  new HashMap<>();
        httpParams.put("userName","1234567890");
        httpParams.put("userPassword","123456");//
        OkHttpManager.asyncRequest("test0.php", httpParams,listener,true);
    }


    private OkHttpManager.ResponseListener listener = new OkHttpManager.ResponseListener() {
        @Override
        public void onResp(JSONObject respons, String uri) {
            Logger.d(TAG, "onResp = "+respons);
//            Logger.d(TAG, "respons = "+respons.optString("userid"));
//            stopProgressDialog();
//            JSONObject json = null;
//            try {
//                json = new JSONObject(respons);
//                Toasts.showToast(json.optString("retMessage"), Toast.LENGTH_SHORT);
//                if (json.optString("retCode").equals("000000")){
////                    MiddleView.getInstance().startCleanActivity(FirstPageAc.class, null);
//
//                }
//            } catch (JSONException e) {
//                Toasts.showToast("返回数据格式不正确", Toast.LENGTH_SHORT);
//                e.printStackTrace();
//            }
        }

        @Override
        public void onErr(String respons, String uri) {
//            stopProgressDialog();
//            Logger.d(TAG, "onErr = "+respons);
//            Toasts.showToast("网络地址错误", Toast.LENGTH_SHORT);

        }
    };
}

package com.pdscjxy.serverapp.manager;

import android.content.Context;
import android.os.Environment;

/**
 * Created by Administrator on 2017/10/26.
 */

public class Constant {

    public static boolean isProduction = false;

    public static Context context;
//    public static final String url = "http://192.168.32.44:8080/MySpringWeb/mvc/";
    public static final String url = "http://47.94.22.205/koudaitu_test/test/";
    public static int widthScreen;
    public static int heightScreen;

    public final static int ERROR_CODE = 9999;
    //
    public static String MESSSAGE = "网络错误";
    public static String MESSSAGEJSON = "数据解析错误";
    public static String path = Environment.getExternalStorageDirectory().toString()+"/ServerApp";

    public static final String logPath = path + "/log/";
    public static final String logException = "logException.txt";
}

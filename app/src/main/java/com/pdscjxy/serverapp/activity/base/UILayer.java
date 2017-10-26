package com.pdscjxy.serverapp.activity.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pdscjxy.serverapp.MainApp;
import com.pdscjxy.serverapp.R;
import com.pdscjxy.serverapp.util.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/26.
 */

public class UILayer {

    public static final int REQUEST_CODE_CALLBACK = 0x1000;
    public static final String EXTRA_ACTIVITY_NAME = "_extra_activity_name";
    public static final String EXTRA_START_CALLBACK = "_extra_start_callback";
    public static final String TITLE_RES_FIRST = "title_res_first";
    public static final String TITLE_RES_SECOND = "title_res_second";


    public static final int ACTIVITY_DEFAULT = -1;
    public static final int ACTIVITY_SETCONTENTVIEW = 1;
    public static final int ACTIVITY_SETCONTENTVIEWFRAMEWITHBOTTOMBUTTON = 2;
    public static final int ACTIVITY_SETCONTENTVIEWSCROLLWITHBOTTOMBUTTON = 3;
    public static final int ACTIVITY_SETCONTENTVIEWSCROLLPULLREFRESHWITHBOTTOMBUTTON = 4;
    public static final int ACTIVITY_SETCONTENTVIEWSCROLLPULLREFRESH = 5;

    public static final String FUNCTION_NAME = "functionName";


    public static final String TAG = "UILayer";

    public static final long VIBRATOR_SHORT = 30;
    public TextView titleTextBack;
//    private int titleRes = R.layout.title2nd;
    TextView lvLayoutTvTitle;
    public ListView lvLayoutListView;
    public String BACK_BTN_TEXT = "backBtnText";
    private LinearLayout ll_bottom_btn_layout;

    private View titleLine;

    public interface OnCNAPSResultListener {
        public void onCNAPSResult(String BankName, String BankID);
    }

    public Activity activity;
    public Fragment fragment;
    private Button primaryButton, secondaryButton;
    private TextView titleText;
    private View base_title;
    private ImageView title_iv_function;
    private TextView title_tv_function;
    private Toast lastToast;
    private SwipeRefreshLayout swipe;
    private ArrayList<Runnable> runnables = new ArrayList<Runnable>();
    /*package*/
    @SuppressLint("HandlerLeak")
    UILayer(Activity act) {
        activity = act;
    }

    /*package*/ void chooseFragment(Fragment f) {
        fragment = f;
    }

    /**
     * 设置Activity的内容View，采用统一自定义的Title
     *
     * @param layoutResID
     */
    public void setContentView(int layoutResID) {
        if (activity instanceof IActivity) {
            ((IActivity) activity).setCustomedLayout(true);
        }
        activity.setContentView(R.layout.activity_base);
//        ViewGroup vg = (ViewGroup) activity.findViewById(R.id.container);
//        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(layoutResID, vg);
//        initAllViewForActivity(vg);
//
//        parentContainer = (SildingFinishLayout) activity.findViewById(R.id.parentContainer);
//        parentContainer.setUILayer(this);
    }


//
//    /**
//     * 修改标题栏所加载的布局文件
//     *
//     * @param titleType 指定标题栏的种类
//     */
//    public void changeTitleRes(@NonNull String titleType) {
//        switch (titleType) {
//            case TITLE_RES_FIRST:
//                titleRes = R.layout.title1st;
//                break;
//            case TITLE_RES_SECOND:
//            default:
//                titleRes = R.layout.title2nd;
//                break;
//        }
//    }

//    /**
//     * 设置Activity的内容View，采用统一自定义的Title，并且在底部有一到两个公共的Button
//     * 中间的内容在FrameLayout的剩余空间中
//     *
//     * @param layoutResID
//     */
//    public void setContentViewFrameWithBottomButton(int layoutResID) {
//        if (activity instanceof IActivity) {
//            ((IActivity) activity).setCustomedLayout(true);
//        }
//        activity.setContentView(R.layout.activity_uibase_frame_with_bottombtn);
//        parentContainer = (SildingFinishLayout) activity.findViewById(R.id.parentContainer);
//        parentContainer.setUILayer(this);
//
//        ViewGroup vg = (ViewGroup) activity.findViewById(R.id.container);
//        LayoutInflater inflater = (LayoutInflater) activity
//                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(layoutResID, vg);
//        initAllViewForActivity(vg);
//    }
//
//    /**
//     * 设置Activity的内容View，采用统一自定义的Title，并且浮动在底部有一到两个公共的Button
//     * 中间的内容会随着高度不够而变为滚动内容
//     *
//     * @param layoutResID
//     */
//    public void setContentViewFrameWithFloatBottomButton(int layoutResID) {
//        if (activity instanceof IActivity) {
//            ((IActivity) activity).setCustomedLayout(true);
//        }
//        activity.setContentView(R.layout.activity_uibase_frame_with_float_bottombtn);
//        ViewGroup vg = (ViewGroup) activity.findViewById(R.id.container);
//        LayoutInflater inflater = (LayoutInflater) activity
//                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(layoutResID, vg);
//        initAllViewForActivity(vg);
//        parentContainer = (SildingFinishLayout) activity.findViewById(R.id.parentContainer);
//        parentContainer.setUILayer(this);
//    }
//
//    /**
//     * 设置Activity的内容View，采用统一自定义的Title，并且在底部有一到两个公共的Button
//     * 中间的内容会随着高度不够而变为滚动内容
//     *
//     * @param layoutResID
//     */
//    public void setContentViewScrollWithBottomButton(int layoutResID) {
//        if (activity instanceof IActivity) {
//            ((IActivity) activity).setCustomedLayout(true);
//        }
//        activity.setContentView(R.layout.activity_uibase_scroll_with_bottombtn);
//        ViewGroup vg = (ViewGroup) activity.findViewById(R.id.container);
//        LayoutInflater inflater = (LayoutInflater) activity
//                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(layoutResID, vg);
//        initAllViewForActivity(vg);
//        parentContainer = (SildingFinishLayout) activity.findViewById(R.id.parentContainer);
//        parentContainer.setUILayer(this);
//    }
//
//    /**
//     * 设置Activity的内容View，采用统一自定义的Title，并且浮动在底部有一到两个公共的Button
//     * 中间的内容会随着高度不够而变为滚动内容
//     *
//     * @param layoutResID
//     */
//    public void setContentViewScrollWithFloatBottomButton(int layoutResID) {
//        if (activity instanceof IActivity) {
//            ((IActivity) activity).setCustomedLayout(true);
//        }
//        activity.setContentView(R.layout.activity_uibase_scroll_with_float_bottombtn);
//        ViewGroup vg = (ViewGroup) activity.findViewById(R.id.container);
//        LayoutInflater inflater = (LayoutInflater) activity
//                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(layoutResID, vg);
//        initAllViewForActivity(vg);
//        parentContainer = (SildingFinishLayout) activity.findViewById(R.id.parentContainer);
//        parentContainer.setUILayer(this);
//    }
//
//    /**
//     * 设置Activity的内容View，采用统一自定义的Title，并且在底部有一到两个公共的Button
//     * 中间的内容为带下拉刷新的ScrollView的Layout布局
//     *
//     * @param layoutResID
//     */
//    public void setContentViewScrollPullRefreshWithBottomButton(int layoutResID) {
//        if (activity instanceof IActivity) {
//            ((IActivity) activity).setCustomedLayout(true);
//        }
//        activity.setContentView(R.layout.activity_uibase_scroll_pull_refresh_with_bottombtn);
//        ViewGroup vg = (ViewGroup) activity.findViewById(R.id.container);
//        LayoutInflater inflater = (LayoutInflater) activity
//                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(layoutResID, vg);
//        initAllViewForActivity(vg);
//        parentContainer = (SildingFinishLayout) activity.findViewById(R.id.parentContainer);
//        parentContainer.setUILayer(this);
//    }
//
//    /**
//     * 设置Activity的内容View，采用统一自定义的Title
//     * 中间的内容为带下拉刷新的ScrollView的Layout布局
//     *
//     * @param layoutResID
//     */
//    public void setContentViewScrollPullRefresh(int layoutResID) {
//        if (activity instanceof IActivity) {
//            ((IActivity) activity).setCustomedLayout(true);
//        }
//        activity.setContentView(R.layout.activity_uibase_scroll_pull_refresh);
//        final ViewGroup vg = (ViewGroup) activity.findViewById(R.id.container);
//        LayoutInflater inflater = (LayoutInflater) activity
//                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(layoutResID, vg);
//        initAllViewForActivity(vg);
//        parentContainer = (SildingFinishLayout) activity.findViewById(R.id.parentContainer);
//        parentContainer.setUILayer(this);
//    }
//
//    /*package*/ View inflateFragmentView(int resId, ViewGroup container, Bundle savedInstanceState) {
//        if (fragment == null) throw new IllegalStateException("当前layout并没有关联fragment");
//        final View view = fragment.getLayoutInflater(savedInstanceState).inflate(resId, container, false);
//        initSwipRefreshView(view);
//        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @SuppressWarnings("deprecation")
//            @Override
//            public void onGlobalLayout() {
//                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                if (fragment != null && fragment instanceof UIBaseFragment) {
//                    ((UIBaseFragment) fragment).onFragmentFirstLayout();
//                }
//            }
//        });
//        return view;
//    }

    private ViewGroup passwordView, nonPasswordView;

    /**
     * 该方法需要在Activity执行setContentView之后调用
     *
     * @return 应用界面的外层控件(innerParent)
     */
//    public ViewGroup getParentView() {
//        ViewGroup v = null;//这是我们定义的布局文件的最外层。parentContainer
//        try {
//            v = (ViewGroup) activity.findViewById(R.id.container);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////		ViewGroup vg = (ViewGroup) v.getParent();//这是安卓系统的外层ViewGroup，也就是我们布局文件最外层所存在的层次。parent
//        return v;
//    }
//
//    /*Activity调用该方法加载默认list_view_layout*/
//    public View inflateListViewLayout(int activityType, @NonNull List<NavigationBean> mList) {
//        return inflateListViewLayout(null, null, activityType, mList);
//    }
//
//    public View inflateListViewLayout(int activityType, ListAdapter.onListAdapterItemClickListener listener, @NonNull List<NavigationBean> mList) {
//        return inflateListViewLayout(null, listener, null, activityType, mList);
//    }
//
//    /*Fragment调用该方法加载默认list_view_layout*/
//    public View inflateListViewLayout(ViewGroup container, Bundle savedInstanceState, @NonNull List<NavigationBean> mList) {
//        return inflateListViewLayout(container, savedInstanceState, ACTIVITY_DEFAULT, mList);
//    }
//
//    public View inflateListViewLayout(ViewGroup container, ListAdapter.onListAdapterItemClickListener listener, Bundle savedInstanceState, @NonNull List<NavigationBean> mList) {
//        return inflateListViewLayout(container, listener, savedInstanceState, ACTIVITY_DEFAULT, mList);
//    }
//
//    private int getTitleHeight() {
//        int margin_top = (int) activity.getResources().getDimension(R.dimen.activity_title_height);
//        return margin_top + getStatusBarHeight();
//    }
//
//    private int getStatusBarHeight() {
//        int result = 0;
//        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            result = activity.getResources().getDimensionPixelSize(resourceId);
//        }
//        return result;
//    }
//
//    public View inflateListViewLayout(ViewGroup container, Bundle savedInstanceState, int activityType, @NonNull List<NavigationBean> mList) {
//        return inflateListViewLayout(container, null, savedInstanceState, activityType, mList);
//    }
//
//    public View inflateListViewLayout(ViewGroup container, ListAdapter.onListAdapterItemClickListener listener, Bundle savedInstanceState, int activityType, @NonNull List<NavigationBean> mList) {
//        int layoutID = R.layout.list_view_layout;
//        int textViewID = R.id.title_tv_in_list_view_layout;
//        int listViewID = R.id.listview_body_in_list_view_layout;
//
//        int titleHeight = R.id.fake_title4fragment;
//
//        View fragmentView = null;
//        if (activity instanceof FragmentActivity && fragment != null) {//如果当前UI是FragmentActivity
//            fragmentView = inflateFragmentView(layoutID, container, savedInstanceState);
//            RelativeLayout tv_titleHeight = (RelativeLayout) fragmentView.findViewById(titleHeight);
//            ViewGroup.LayoutParams params = tv_titleHeight.getLayoutParams();
//            params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
//            params.height = getTitleHeight();
//            tv_titleHeight.setLayoutParams(params);
//
//            lvLayoutTvTitle = (TextView) fragmentView.findViewById(textViewID);
//            lvLayoutListView = (ListView) fragmentView.findViewById(listViewID);
//        } else {//如果当前UI是Activity
//            switch (activityType) {
//                case ACTIVITY_SETCONTENTVIEW:
//                    setContentView(layoutID);
//                    break;
//                case ACTIVITY_SETCONTENTVIEWFRAMEWITHBOTTOMBUTTON:
//                    setContentViewFrameWithBottomButton(layoutID);
//                    break;
//                case ACTIVITY_SETCONTENTVIEWSCROLLPULLREFRESH:
//                    setContentViewScrollPullRefresh(layoutID);
//                    break;
//                case ACTIVITY_SETCONTENTVIEWSCROLLPULLREFRESHWITHBOTTOMBUTTON:
//                    setContentViewScrollPullRefreshWithBottomButton(layoutID);
//                    break;
//                case ACTIVITY_SETCONTENTVIEWSCROLLWITHBOTTOMBUTTON:
//                    setContentViewScrollWithBottomButton(layoutID);
//                    break;
//                default:
//                    return null;
//            }
//            lvLayoutTvTitle = (TextView) activity.findViewById(textViewID);
//            lvLayoutListView = (ListView) activity.findViewById(listViewID);
//        }
//        if (lvLayoutTvTitle == null || lvLayoutListView == null)
//            return null;
//        if (mList != null) {
//            ListAdapter adapter = new ListAdapter(this, mList);
//            adapter.setOnListAdapterItemClickListener(listener);
//            lvLayoutListView.setAdapter(adapter);
//        }
//        return fragmentView;
//    }
//
//    //条目点击事件
//    public void onListAdapterItemClick(NavigationBean navigationBean) {
//        Intent intent = null;
//        String webViewTitle;
//
//        int activityType = navigationBean.getActivityType();
//        switch (activityType) {
//            case WebViewActivity.DEMONSTRATIVE_WEB_VIEW://普通展示类的H5页面
//                webViewTitle = navigationBean.getText();
//                String webViewDemonstrativeParam = navigationBean.getWebViewDemonstrativeParam();
//                startDemonstrativeWebViewActivity(webViewTitle, webViewDemonstrativeParam);
//                return;
//            case WebViewActivity.INTERACTIVE_WEB_VIEW://绑定商户的H5页面
//                startBindPosWebViewActivity(null, null);
//                Logger.e(TAG, "startWebViewActivity finished");
//                return;
//            default:
//                break;
//        }
//
//
//        String className = navigationBean.getTarget();
//        Logger.i("myOnItemClickListener", "className:" + className);
//        if (!TextUtils.isEmpty(className)) {
//            intent = getIntent(className);
//        } else if (!TextUtils.isEmpty(navigationBean.getAction())) {
//            intent = getIntent(navigationBean.getAction(), null, navigationBean.getCategorys());
//        }
//        Logger.e(TAG, "intent==null:" + (intent == null) + "   navigationBean.getTarget():" + className + "   navigationBean.getAction():" + navigationBean.getAction() + "   navigationBean.getCategorys():" + navigationBean.getCategorys() + "   navigationBean.getText():" + navigationBean.getText());
//        if (intent != null) {
//            intent.putExtra(NavigationBean.ITEM_TEXT, navigationBean.getText());
//            startCallbackActivity(intent);
//            Logger.e(TAG, "ListView默认的点击startCallbackActivity finished");
//        }
//    }

    /**
     * 获得显示意图intent
     *
     * @param className packageName + ClassName
     * @return intent
     */
    public Intent getIntent(@NonNull String className) {
        Intent intent = null;
        try {
            Logger.i(TAG, "activity:" + activity.getClass().getSimpleName());
            intent = new Intent(activity, Class.forName(className));
        } catch (ClassNotFoundException e) {
            Logger.e(TAG, "反射获取显示意图失败");
            e.printStackTrace();
        }
        Logger.e(TAG, "intent == null:" + (intent == null));
        return intent;
    }

    /**
     * 使用隐式意图进行Action和Category匹配
     *
     * @param action
     * @param b
     * @param collection 页面跳转需要传入数据，可以为null
     */
    private Intent getIntent(@NonNull String action, @Nullable Bundle b, @Nullable Collection<String> collection) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (collection != null) {
            for (String ca : collection)
                intent.addCategory(ca);
        }
        if (b != null) {
            intent.putExtras(b);
        }
        return intent;
    }

    /**
     * 列表内容为动态，页面跳转使用隐式意图进行Action和Category匹配
     *
     * @param action
     * @param b          Key = "data"
     * @param collection 页面跳转需要传入数据，可以为null
     */
    private void getIntent(@NonNull String action, @Nullable Bundle b, @Nullable String... collection) {
        List<String> strings = null;
        if (collection != null) {
            strings = Arrays.asList(collection);
        }
        getIntent(action, b, strings);
    }


    public TextView getLvLayoutTvTitle() {
        return lvLayoutTvTitle;
    }

    public ListView getLvLayoutListView() {
        return lvLayoutListView;
    }

    /**
     * 当下次返回该界面时，执行Runnable，请注意，该方法尽量在startActivity之后调用
     * WARNING FIXME 该方法可能造成内存泄露，Runnable r内请不要有外部对象的引用，该引用被当前acitivity持续持有
     * 该Runnable未提供回收机制，只能随着activity自动回收
     *
     * @param r
     */
    public void requireRunWhenComeBack(Runnable r) {
        runnables.add(r);
    }

//    /**
//     * 获取底部的PrimaryButton的引用
//     *
//     * @return Button
//     */
//    public Button getPrimaryBtn() {
//        activity.findViewById(R.id.ll_bottom_btn_layout).setVisibility(View.VISIBLE);
//        activity.findViewById(R.id.frame_primary_submit).setVisibility(View.VISIBLE);
//        return primaryButton;
//    }
//
//    /**
//     * 获取底部的SecondaryButton的引用
//     *
//     * @return Button
//     */
//    public Button getSecondaryBtn() {
//        activity.findViewById(R.id.ll_bottom_btn_layout).setVisibility(View.VISIBLE);
//        activity.findViewById(R.id.frame_secondary_submit).setVisibility(View.VISIBLE);
//        return secondaryButton;
//    }

    /**
     * 获取顶部的返回按钮
     *
     * @return View
     */
//    public View getBackBtn() {
//        backButton.setVisibility(View.VISIBLE);
//        return backButton;
//    }

    /**
     * 获取顶部标题
     *
     * @return TextView
     */
    public TextView getTitle() {
        if (titleText != null)
            titleText.setVisibility(View.VISIBLE);
        return titleText;
    }
    /**
     * 获取顶部线
     *
     * @return View
     */
    public View getTitleLine() {
        if (titleLine != null)
            titleLine.setVisibility(View.VISIBLE);
        return titleLine;
    }

//    public String getSessionId() {
//        return ((MainApp) activity.getApplication()).getSessionId();
//    }

    /**
     * 获取顶部的返回按钮文字控件
     *
     * @return View
     */
    public TextView getBackBtnTextView() {
        return titleTextBack;
    }

    /**
     * 修改Textview按钮文字控件的图片
     *
     * @return View
     */
    public void setBackBtnTextViewLeftImg(Context mContext, TextView tv, int imgid) {
        Drawable nav_up=mContext.getResources().getDrawable(imgid);
        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        tv.setCompoundDrawables(nav_up, null, null, null);

    }
    /**
     * 获取当前页面的下拉刷新layout
     *
     * @return SwipeRefreshLayout
     */
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipe;
    }


    /**
     * 在不需要Session对象只是判断Session是否为null
     * 使用这个方法，此方法在Session为null后会使客户端
     * 退到登录界面
     *
     * @return
     */
//    public boolean isSessionEmpty() {
//        Session session = getLoginSession();
//        if (session == null) {
//            toast(R.string.login_timeout);
//            signOff();
//        }
//        return session == null;
//    }


    /**
     * 启动网页界面，默认使用的是协议页面
     *
     * @param title 网页title
     * @param value 网页具体参数
     */
//    public void startDemonstrativeWebViewActivity(String title, String value) {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("html", value);
//        startWebViewActivity(title, Http.BASE_URL + API.showHtml, params, null, WebViewActivity.DEMONSTRATIVE_WEB_VIEW, null);
//    }

    /**
     * 打开活动页面(首页轮播广告及优惠活动页的详情页)
     *
     * @param params
     */
//    public void startPreferentialWebViewActivity(String title, Map<String, Object> params) {
//        startWebViewActivity(title, null, params, null, WebViewActivity.PREFERENTIAL_WEB_VIEW, null);
//    }

    /**
     * 绑定新大POS的H5WebViewActivity
     *
     * @param urlExtra 绑定记录中某个条目继续验证时 用于拼接url的字段；如果是新绑定，传空
     * @param cls      打开新Activity时所要关闭的最后一个Activity
     */
//    public void startBindPosWebViewActivity(@Nullable String urlExtra, @Nullable Class cls) {
//        Options options = new Options();
//        options.put(K.isNeedSession, true);
//        options.put(K.isNeedUsername, true);
//        if (TextUtils.isEmpty(urlExtra))
//            urlExtra = API.appMerchant;
//        if (!urlExtra.startsWith("/"))
//            urlExtra = "/" + urlExtra;
//        startWebViewActivity("绑定商户", Http.H5_BIND_POS_BASE_URL + urlExtra, null, options, WebViewActivity.INTERACTIVE_WEB_VIEW, cls);
//    }

    /**
     * 启动webView
     *
     * @param title 界面显示title
     * @param url   请求url
     * @param cls   打开新Activity时所要关闭的最后一个Activity
     */
//    public void startWebViewActivity(String title, String url, Map<String, Object> params, Options options, int webViewType, @Nullable Class cls) {
//        Intent intent = new Intent(activity, WebViewActivity.class);
//
//        Session session = SessionManager.newSession(WebViewActivity.TAG, -1);
//        session.put(K.webViewActivityTitle, title);
//        session.put(K.webViewActivityURL, url);
//        session.put(K.webViewActivityParams, params);
//        session.put(K.webViewActivityOptions, options);
//        session.put(K.webViewType, webViewType);
//
//        if (cls != null)
//            startResponseActivityFromAssignedActivity(intent, cls);
//        else
//            startCallbackActivity(intent);
//    }

    /*获取Title右上角的ImageView按钮*/
    public ImageView getTitleFunctionImageView() {
        title_iv_function.setVisibility(View.VISIBLE);
        return title_iv_function;
    }

    /*获取Title右上角的TextView按钮*/
    public TextView getTitleFunctionText() {
        title_tv_function.setVisibility(View.VISIBLE);
        return title_tv_function;
    }

    /**
     * 隐藏含有输入框的Activity的输入法键盘
     */
    public void hideInputMethod() {
        View v = activity.getCurrentFocus();
        if (v != null) {
            InputMethodManager inputManager = (InputMethodManager) activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 展现含有输入框的Activity的输入法键盘
     */
    public void showInputMethod() {
        View v = activity.getCurrentFocus();
        if (v != null) {
            InputMethodManager inputManager = (InputMethodManager) activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(v, 0);
        }
    }

    // FIXME: 2016/5/20 需要完善的是startResponseActivityFromAssignedActivity方法，这里就不用做判断activity.getClass() == MainActivity.class了

    /**
     * @param finishApp 是否退出应用
     */
    public void signOff(boolean finishApp) {
//        if (activity.isFinishing()) {
//            Logger.e(TAG, "activity:" + activity.getClass().getSimpleName() + "   isFinishing");
//            return;
//        }
//        Logger.e(TAG, "activity:" + activity.getClass().getSimpleName() + "   notFinishing");
//        POS.logout(this);
//        UpdateModel.fileOfAPK = null;
//        Intent i = new Intent(activity, LoginActivity.class);
//        if (activity.getClass() == MainActivity.class) {
//            Logger.e(TAG, "当前activity为MainActivity");
//            activity.finish();//此处原为onBackPressed，可能容易导致崩溃
//            if (!finishApp)
//                startActivity(i);
//        } else if (Utils.isBaseActivityOfLauncher(MainActivity.class, activity)) {
//            Logger.e(TAG, "当前activity所处的栈的栈底是MainActivity");
//            if (finishApp)
//                startResponseActivityFromAssignedActivity(i, LoginActivity.class);
//            else
//                startResponseActivityFromAssignedActivity(i, MainActivity.class);
//        } else {
//            Logger.e(TAG, "当前activity所处的栈的栈底不是MainActivity(异常情况signoff)");
//        }
    }

//    /**
//     * 返回Login会话
//     *
//     * @return
//     */
//    public Session getLoginSession() {
//        return ((MainApp) activity.getApplicationContext()).getLoginSession();
//    }

    /**
     * 设置自定义的Title
     *
     * @param title
     */
    public void setTitle(CharSequence title) {
        if (titleText != null)
            titleText.setText(title);
        if (fragment != null) {
            //去掉动画
//			Animation anim = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
//			titleText.clearAnimation();
//			anim.setStartOffset(0);
//			titleText.startAnimation(anim);
        }
    }

    /**
     * 设置自定义的Title
     *
     * @param resid
     */
    public void setTitle(int resid) {
        if (titleText != null)
            titleText.setText(resid);
    }

    /**
     * 退出过场动画，在finish之后调用才有效
     *
     * @param anims 可以为空，int enterAnim, int exitAnim
     */
//    public void quitAnimation(@AnimRes int... anims) {
//        int enterAnim = R.anim.slide_in_left;
//        int exitAnim = R.anim.slide_out_right;
//        if (null != anims && anims.length == 2) {
//            enterAnim = anims[0];
//            exitAnim = anims[1];
//        }
//        activity.overridePendingTransition(enterAnim, exitAnim);
//    }

    /**
     * 进入过场动画，在startActivity[ForResult]之后调用才有效
     *
     * @param anims 可以为空，int enterAnim, int exitAnim<br>
     */
    public void enterAnimation(@AnimRes int... anims) {
        int enterAnim = R.anim.slide_in_right;
        int exitAnim = R.anim.slide_out_left;
        if (null != anims && anims.length == 2) {
            enterAnim = anims[0];
            exitAnim = anims[1];
        }
        activity.overridePendingTransition(enterAnim, exitAnim);
    }

//    /**
//     * 退出过场动画，在finish之后调用才有效，效果和quitAnimation相反
//     */
//    public void quitReverseAnimation() {
//        activity.overridePendingTransition(R.anim.slide_in_right_reverse, R.anim.slide_out_left_reverse);
//    }

    /**
     * 启动新的Activity，该操作会自带转场动画
     *
     * @param intent
     */
    public void startActivity(Intent intent) {
        if (activity instanceof FragmentActivity && fragment != null) {
            fragment.startActivity(intent);
        } else {
//            ((UIBaseActivity)activity).isSkip = true;
            activity.startActivity(intent);
        }
        enterAnimation();
    }


    /**
     * 启动新的Activity，该操作会自带转场动画
     *
     * @param intent
     */
    public void startActivityWithCustomAnim(Intent intent, @AnimRes int... anims) {
        if (activity instanceof FragmentActivity && fragment != null) {
            fragment.startActivityForResult(intent, REQUEST_CODE_CALLBACK);
        } else {
//            ((UIBaseActivity)activity).isSkip = true;
            activity.startActivityForResult(intent, REQUEST_CODE_CALLBACK);
        }
        enterAnimation(anims);
    }

    /**
     * 启动一个插入型Activity，自带转场
     * 例如，当你在A中启动B，如果你想在B中启动C，同时关闭A
     * 则在A中使用startCallbackActivity启动B
     * 在B中使用startResponseActivity启动C即可
     *
     * @param intent
     */
    public void startCallbackActivity(Intent intent) {
        Logger.e(TAG, "intent.getComponent()==null:" + (intent.getComponent() == null));
        Logger.i(TAG, activity.getClass().getName() + " => " + intent.getComponent().getClassName());
        intent.putExtra(BACK_BTN_TEXT, getTitle().getText().toString());//将自己的title传给打开的页面作为返回按钮的文本内容
        Logger.i(TAG, "TITLE：  " + getTitle().getText().toString());
        if (activity instanceof FragmentActivity && fragment != null) {
            fragment.startActivityForResult(intent, REQUEST_CODE_CALLBACK);
        } else {
//            ((UIBaseActivity)activity).isSkip = true;
            activity.startActivityForResult(intent, REQUEST_CODE_CALLBACK);
        }
        enterAnimation();
    }

    /**
     * 启动一个Activity，自带转场，同时关闭自己
     * 例如，当你在A中启动B，如果你想在B中启动C，同时关闭B
     * 则在A中使用startCallbackActivity启动B
     * 在B中使用startResponseActivity，关闭B并启动C即可
     *
     * @param intent
     */
    public void startResponseActivity(Intent intent) {
        Logger.i(TAG, "startResponseActivity：" + "   intent is null： " + (intent == null));
        activity.setResult(Activity.RESULT_FIRST_USER, intent);
        activity.finish();
    }

    /**
     * 关闭从[自己到目标clazz]之间的所有Activity，并启动一个新的Activity
     * 例如，当你在A中启动B，B中启动C->D->E->F。此时想启动Z，同时关闭之前除A以外所有Activity
     * 则在从A到Y启动Activity都使用startCallbackActivity方法。而在Y->Z使用
     * startResponseActivityFromAssignedActivity，第一个参数选Z的intent，
     * 第二个参数选用想要关闭的最前面一个Activity也就是B。
     * <p>
     * 如果仅仅是想关闭从Z到A的所有Activity而不开新的Activity，则此处传入一个不带class和Action的干净的 Intent 即可
     *
     * @param clazz  打开新Activity时所要关闭的最后一个Activity
     * @param intent
     */
    public void startResponseActivityFromAssignedActivity(Intent intent, Class<? extends Activity> clazz) {
        Logger.i(TAG, "startResponseActivityFromAssignedActivity  intent:" + intent + " clazz:" + clazz.getName());
        if (activity.getClass().getCanonicalName().equals(clazz.getCanonicalName())){
            startResponseActivity(intent);
        }else {
            activity.setResult(Activity.RESULT_FIRST_USER, intent);
            intent.putExtra(EXTRA_ACTIVITY_NAME, clazz.getCanonicalName());
            activity.finish();
        }
    }

    /**
     * Toast
     *
     * @param content
     */
    public void toast(String content) {
        if (lastToast == null) {
            lastToast = Toast.makeText(activity.getApplicationContext(), content, Toast.LENGTH_SHORT);
        } else {
            lastToast.setText(content);
        }
        lastToast.show();
    }

    public void toast(int resId) {
        if (lastToast == null) {
            lastToast = Toast.makeText(activity.getApplicationContext(), resId, Toast.LENGTH_SHORT);
        } else {
            lastToast.setText(resId);
        }
        lastToast.show();
    }

//    private void initCustomTitle() {
//        activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, titleRes); // title为自己标题栏的布局
//        base_title = activity.findViewById(R.id.base_title);
//        titleText = (TextView) activity.findViewById(R.id.title_text);
//        if (titleText != null)
//            titleText.setText(activity.getTitle());
//        //去掉动画
////		Animation anim = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
////		titleText.startAnimation(anim);
//
//        titleTextBack = (TextView) activity.findViewById(R.id.title_text_back);
//        titleLine=activity.findViewById(R.id.main_line_view);
//        title_iv_function = (ImageView) activity.findViewById(R.id.title_iv_function);
//        title_tv_function = (TextView) activity.findViewById(R.id.title_tv_function);
//    }

    public View getBaseTitle() {
        return base_title;
    }

//    public void setLeftOrRight(boolean isLeft) {
//        if (isLeft) {
//            Logger.d(TAG, "0");
//            setAlpha(0);
//        } else {
//            Logger.d(TAG, "1");
//            setAlpha(1);
//        }
//
//    }

    /**
     * 获取SD卡根路径
     *
     * @return
     */
    public String getSDPath() {
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            return (Environment.getExternalStorageDirectory()).toString();
        }
        return null;
    }

//    public void setTitleAnimation(int location) {
//        setAlpha(getAlpha(location));
//    }

//    private void setAlpha(float alpha) {
////        base_title.getBackground().setAlpha((int)(255 * alpha));
//        if (base_title != null)
//            base_title.setAlpha(alpha);
//    }
//
//    private float getAlpha(float location) {
//        return (location / (float) ((MainApp) activity.getApplication()).getScreenWidth() + 1);
//    }

//    private void initBottomButton() {
//        ll_bottom_btn_layout = (LinearLayout) activity.findViewById(R.id.ll_bottom_btn_layout);
//        primaryButton = (Button) activity.findViewById(R.id.primary_submit);
//        secondaryButton = (Button) activity.findViewById(R.id.secondary_submit);
//    }

    //获取底部按钮所在的LinearLayout
    public LinearLayout getBottomBtnLayout() {
        return ll_bottom_btn_layout;
    }

    private void initAllViewForActivity(final ViewGroup vg) {
//        initCustomTitle();
//        initBottomButton();
//        initSwipRefreshView(null);
        notifyFirstLayout(vg);
    }

    private void notifyFirstLayout(final ViewGroup vg) {
        vg.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                vg.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (activity != null && activity instanceof IActivity) {
                    ((IActivity) activity).onActivityFirstLayout();
                }
            }
        });
    }

//    private void initSwipRefreshView(View view) {
//        if (view != null) {
//            swipe = (SwipeRefreshLayout) view.findViewById(R.id.pull_refresh_view);
//        } else {
//            swipe = (SwipeRefreshLayout) activity.findViewById(R.id.pull_refresh_view);
//        }
//        if (swipe == null) {
//            return;
//        }
//        swipe.setColorSchemeResources(R.color.holo_blue_bright,
//                R.color.holo_green_light, R.color.holo_orange_light,
//                R.color.holo_red_light);
//    }

    //只有ui.onActivityResult里面的代码都不执行才会交给外部调用者的super.onActivityResult处理
    /*package*/ boolean onActivityResult(int requestCode, int resultCode, Intent data) {
//		Logger.i(TAG, TAG+"    onActivityResult ------ Intent data is null： "+ (data==null) + "  requestCode:" + Integer.valueOf(requestCode + "", 16) +"   resultCode:" + resultCode);
        Logger.i(TAG, "onActivityResult ------ Intent data is null： " + (data == null) + "  requestCode:" + requestCode + "   resultCode:" + resultCode);
        if (requestCode == REQUEST_CODE_CALLBACK) {
            if (resultCode == Activity.RESULT_FIRST_USER) {
                if (data != null) {
                    try {
                        String activityName = data.getStringExtra(EXTRA_ACTIVITY_NAME);
                        Logger.e(TAG, "onActivityResult  this Activity name:" + activity.getClass().getCanonicalName() + "   target Activity name:" + activityName);
                        if (!TextUtils.isEmpty(activityName)) {
                            Logger.i(TAG, "UILayer的onActivityResult方法执行了，并且activityName不为空");
                            @SuppressWarnings("unchecked")
                            Class<? extends Activity> clazz = (Class<? extends Activity>) Class.forName(activityName);
                            if (activity.getClass().getCanonicalName().equals(activityName)) {
                                Logger.i(TAG, "onActivityResult");
                                if (data.getBooleanExtra(EXTRA_START_CALLBACK, false)) {
                                    data.removeExtra(EXTRA_ACTIVITY_NAME);
                                    startResponseActivity(data);
                                    return true;
                                    /*EXTRA_START_CALLBACK 为 true 代表在名为 activityName 的activity结束之后，将 data 交给上一个activity去处理。
                                     反之，如果为 false 代表会在当前activity结束后立即在上个activity基础上用 data 再开启一个activity。*/
                                }
                                activity.finish();
                                if (isIntentValid(data)) {
                                    startActivity(data);
                                }
                                return true;
                            }
                            startResponseActivityFromAssignedActivity(data, clazz);
                            return true;
                        }
                        //FIXME
                    } catch (Exception ignore) {
                    }
                    if (isIntentValid(data)) {
                        startCallbackActivity(data);//只要data不为空就会开启一个新的Activity
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /*package*/ void onStart() {
        for (Runnable r : runnables) {
            r.run();
        }
        runnables.clear();
    }

    void onResume() {
        if (titleTextBack == null)
            return;
        String backBtnText = activity.getIntent().getStringExtra(BACK_BTN_TEXT);
        Logger.i(TAG, "返回按钮text:" + backBtnText);
        titleTextBack.setText("返回");
        titleTextBack.setVisibility(View.VISIBLE);
        titleTextBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideInputMethod();
                activity.onBackPressed();
            }
        });
    }

    /**
     * fragment的onResume会调用的方法
     */
    void onFragmentResume() {
        checkLoginStatus();
    }

    /**
     * 检查用户loginsession状态，如果loginsession不存在而又以MainActivity为任务栈底时就下线
     *
     * @return 是否存在loginSession
     */
    public boolean checkLoginStatus() {
        Logger.i(TAG, activity.getClass().getSimpleName() + "   checkLoginStatus");
//        Session loginSession = getLoginSession();
//        boolean result;
//        if (loginSession == null) {
//            Logger.i(TAG, "session为空,下线");
//            result = false;
//        } else {
//            result = true;
//        }
//
//        if (Utils.isBaseActivityOfLauncher(MainActivity.class, activity) && !result) {
//            signOff();
//        }
//        return result;
        return true;
    }

    /*package*/ void onStop() {
    }

    /**
     * 启动振动器
     *
     * @param time 振动时长
     */
    public void vibrate(long time) {
        if (time == 0) {
            time = VIBRATOR_SHORT;
        }
        Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(time);
    }



    public boolean isIntentValid(Intent intent) {//判断intent中是否包含一个明确的意图，即是否有明确的Activity要跳转
        if (intent == null || intent.getComponent() == null)
            return false;
        return !TextUtils.isEmpty(intent.getComponent().getClassName() + intent.getAction());
    }



}

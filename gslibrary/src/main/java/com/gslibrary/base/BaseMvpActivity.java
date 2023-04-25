package com.gslibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.gslibrary.utils.LogUtils;
import com.gslibrary.utils.Toasts;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * MVPbase类
 *
 * @param <T>
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends FragmentActivity  {
    /**
     * 标示
     */
    protected static final String TAG = "BaseActivity";
    /**
     * 所有已存在的Activity
     */
    protected static ConcurrentLinkedQueue<Activity> allActivity = new ConcurrentLinkedQueue<>();
    /**
     * 同时有效的界面数量
     */
    protected static final int validActivityCount = 15;
    /**
     * 屏幕的宽高
     */
    protected int mScreenWidth;
    protected int mScreenHeight;
    /**
     * Context对象
     */
    protected Context mContext;

    /**
     * 控制器对象
     */
    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // Activity队列管理，如果超出指定个数，获取并移除此队列的头（队列中时间最长的）。
        if (allActivity.size() >= validActivityCount) {
            Activity act = allActivity.poll();
            act.finish();// 结束
        }
        allActivity.add(this);
        printAllActivityName();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        try {// 设置布局文件、初始化布局文件中的控件、初始化控件的监听、进行数据初始化。（子类重写这些方法）
            setContentView();
            initView();
            initListen();
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage(), e);
        }
        presenter = initPresenter();
    }

    /**
     * 设置布局文件
     */
    protected abstract void setContentView();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置监听事件
     */
    protected abstract void initListen();

    /**
     * 设置控制器
     */
    protected abstract T initPresenter();

    /**
     * 控制台上打印
     */
    private static void printAllActivityName() {
        for (Activity activity : allActivity) {
            LogUtils.d(TAG, activity.getClass().getName());
        }
    }

    /**
     * 设置是否全屏显示
     */
    public void setStatesVisibislity() {
        // 隐藏状态栏
        // 定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        // 获得当前窗体对象
        Window window = this.getWindow();
        // 设置当前窗体为全屏显示
        window.setFlags(flag, flag);
    }

    /**
     * 打印异常
     *
     * @param e 要打印的异常栈
     */
    protected void error(Exception e) {
        LogUtils.e(this.getClass().getName(), e.getMessage(), e);
    }

    /**
     * Activity跳转
     *
     * @param intent      传递参数
     * @param end         跳转类
     * @param requestCode 请求码
     */
    public void toActivity(Intent intent, Class<? extends Activity> end, int requestCode) {
        if (null == intent) {
            intent = new Intent();
        }
        intent.setClass(this, end);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转到另外一个Activity
     *
     * @param end 要跳转的Activity的class
     */
    public void toActivity(Class<? extends Activity> end) {
        Intent intent = new Intent();
        toActivity(intent, end);
    }

    /**
     * 跳转到另外一个Activity
     *
     * @param intent {@link Intent}
     * @param end    要跳转的Activity的class
     */
    public void toActivity(Intent intent, Class<? extends Activity> end) {
        if (null == intent) {
            toastShow("请使用toActivity(Class<? extends BaseActivity> end)");
        }
        intent.setClass(this, end);
        startActivityForResult(intent, 0);
    }

    /**
     * 根据信息弹出一个提示框
     *
     * @param msg 要提示的信息
     */
    public void toastShow(String msg) {
        Toasts.showShort(msg);
    }

    /**
     * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 当用户点击返回时，结束掉当前Activity
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出系统
     */
    public void exitApp() {
        finishAll();
    }

    /**
     * 退出当前activity
     *
     * @see android.app.Activity#finish()
     */
    public void finish() {
        try {
            super.finish();
            // 软键盘隐藏
            if (null != this.getCurrentFocus() && null != this.getCurrentFocus().getWindowToken()) {
                InputMethodManager in = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
                in.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
            // 从Activity集合中清理出已结束的Activity
            if (allActivity != null && allActivity.size() > 0 && allActivity.contains(this)) {
                allActivity.remove(this);
            }
            for (Activity a : allActivity) {
                LogUtils.d("finish", a.getClass().getName());
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage(), e);
        }
    }

    /**
     * 结束所有activity
     */
    public static void finishAll() {
        // 结束Activity
        try {
            for (Activity act : allActivity) {
                allActivity.remove(act);
                act.finish();
                printAllActivityName();
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage(), e);
        }
    }

    /**
     * 判断activity是否已经结束
     *
     * @param act
     * @return
     */
    public static boolean contains(Class<?> act) {
        // 结束Activity
        try {
            for (Activity ele : allActivity) {
                if (ele.getClass().getName().equals(act.getName())) {
                    return Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage(), e);
        }
        return Boolean.FALSE;
    }

    /**
     * 取得版本名称
     *
     * @return
     */
    public String getVersionName() {
        if (null != getPackageInfo()) {
            return getPackageInfo().versionName;
        } else {
            return "";
        }
    }

    private PackageInfo getPackageInfo() {
        PackageManager manager = this.getPackageManager();
        try {
            return manager.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.d(TAG, e.getMessage());
        }
        return null;
    }

    /**
     * 动态设置listView的高度 count 总条目
     */
    public void setListViewHeight(ListView listView, BaseAdapter adapter, int count) {
        int totalHeight = 0;
        for (int i = 0; i < count; i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * count);
        listView.setLayoutParams(params);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.dettach();
    }

}
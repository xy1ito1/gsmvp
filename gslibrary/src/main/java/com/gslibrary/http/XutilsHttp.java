package com.gslibrary.http;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.Map;


/*********************************************
 * 通用网络请求
 ***       Created by HC on 2017/4/14.       ***
 *********************************************/

public class XutilsHttp {
    private volatile static XutilsHttp instance;
    private Handler handler;

    private XutilsHttp() {
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * * 单利模式
     * * @return
     */
    public static XutilsHttp getInstance() {
        if (instance == null) {
            synchronized (XutilsHttp.class) {
                if (instance == null) {
                    instance = new XutilsHttp();
                }
            }
        }
        return instance;
    }

    /**
     * 异步get请求
     *
     * @param url
     * @param maps
     * @param callback
     */
    public void get(String url, Map<String, String> maps, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        if (null != maps && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().get(params, new Callback.CommonCallback<String>() {
            private boolean hasError = false;
            private String result = null;

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.toString());
                hasError = true;
                Toast.makeText(x.app().getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                callback.onFail(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    callback.onResponse(result);
                } else {
                    callback.onFail(result);
                }
            }
        });
    }

    /**
     * 带缓存数据的异步get请求
     *
     * @param url
     * @param maps
     * @param ifCache   是否缓存
     * @param cacheTime 缓存存活时间
     * @param callback
     */
    public void getCache(String url, Map<String, String> maps, final boolean ifCache, long cacheTime, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        params.setCacheMaxAge(cacheTime);
        if (null != maps && maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().get(params, new Callback.CacheCallback<String>() {
            private boolean hasError = false;
            private String result = null;

            @Override
            public boolean onCache(String result) {
                if (ifCache && null != result) {
                    this.result = result;
                }
                // true: 信任缓存数据, 不在发起网络请求; false不信任缓存数据.
                return ifCache;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                LogUtil.e(ex.toString());
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                callback.onFail(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    callback.onResponse(result);
                } else {
                    callback.onFail(result);
                }
            }
        });
    }

    /**
     * 异步post请求
     *
     * @param url
     * @param maps
     * @param callback
     */
    public void post(String url, Map<String, String> maps, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        if (null != maps && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            private boolean hasError = false;
            private String result = null;

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                LogUtil.e(ex.toString());
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                callback.onFail(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    callback.onResponse(result);
                } else {
                    callback.onFail(result);
                }
            }
        });
    }

    /**
     * 带缓存数据的异步post请求
     *
     * @param url
     * @param maps
     * @param ifCache   是否缓存
     * @param cacheTime 缓存存活时间
     * @param callback
     */
    public void postCache(String url, Map<String, String> maps, final boolean ifCache, long cacheTime, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        params.setCacheMaxAge(cacheTime);
        if (null != maps && maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().get(params, new Callback.CacheCallback<String>() {
            private boolean hasError = false;
            private String result = null;

            @Override
            public boolean onCache(String result) {
                if (ifCache && null != result) {
                    this.result = result;
                }
                // true: 信任缓存数据, 不在发起网络请求; false不信任缓存数据.
                return ifCache;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                LogUtil.e(ex.toString());
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                callback.onFail(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    callback.onResponse(result);
                } else {
                    callback.onFail(result);
                }
            }
        });
    }

    /**
     * 下载文件
     *
     * @param url
     * @param filePath
     * @param callback
     */
    public void downFile(String url, String filePath, final XDownLoadCallBack callback) {
        RequestParams params = new RequestParams(url);
        params.setSaveFilePath(filePath);
        params.setAutoRename(true);
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                //下载完成会走该方法
                callback.onSuccess(result);
            }

            @Override
            public void onError(final Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.toString());
                callback.onFail(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }

            //网络请求之前回调
            @Override
            public void onWaiting() {
            }

            //网络请求开始的时候回调
            @Override
            public void onStarted() {
                callback.onstart();
            }

            //下载的时候不断回调的方法
            @Override
            public void onLoading(final long total, final long current, final boolean isDownloading) {
                //当前进度和文件总大小
                callback.onLoading(total, current, isDownloading);
            }
        });
    }

    /**
     * 文件上传
     *
     * @param url
     * @param maps
     * @param file
     * @param callback
     */
    public void upLoadFile(String url, Map<String, String> maps, Map<String, File> file, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        if (file != null) {
            for (Map.Entry<String, File> entry : file.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue().getAbsoluteFile());
            }
        }
        // 有上传文件时使用multipart表单, 否则上传原始文件流.
        params.setMultipart(true);
        x.http().post(params, new Callback.CommonCallback<String>() {
            private boolean hasError = false;
            private String result = null;

            @Override
            public void onSuccess(String result) {
                if (result == null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                LogUtil.e(ex.toString());
                callback.onFail(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    callback.onResponse(result);
                } else {
                    callback.onFail(result);
                }
            }
        });
    }

    /**
     * 上传Json串到服务器
     *
     * @param url
     * @param maps 将需要传的各个参数放在Map集合里面
     */
    public void upLoadJson(String url, Map<String, String> maps, final XCallBack callback) {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                js_request.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams(url);
        params.setAsJsonContent(true);
        params.setBodyContent(js_request.toString());

        x.http().post(params, new Callback.CommonCallback<String>() {//发起传参为json的post请求，
            // Callback.CacheCallback<String>的泛型为后台返回数据的类型，
            // 根据实际需求更改
            private boolean hasError = false;
            private String result = null;

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                LogUtil.e(ex.toString());
                callback.onFail(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (!hasError && result != null && callback != null) {
                    callback.onResponse(result);
                } else {
                    callback.onFail(result);
                }
            }
        });

    }

}
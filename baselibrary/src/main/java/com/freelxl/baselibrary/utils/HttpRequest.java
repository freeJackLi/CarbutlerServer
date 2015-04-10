package com.freelxl.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.freelxl.baselibrary.BuildConfig;
import com.freelxl.baselibrary.bean.BaseJson;
import com.freelxl.baselibrary.config.ConstantValue;
import com.freelxl.baselibrary.config.GlobalParams;
import com.freelxl.baselibrary.dialog.LoadingDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.Map;

/**
 * HTTP请求的工具类
 */
public abstract class HttpRequest<T extends BaseJson> {

    // 超时时间
    private static final int HTTP_TIMEOUT = 10 * 1000;

    // volley queue
    private static RequestQueue queue;

    // 请求路径
    String path;

    // 请求参数
    Map<String, String> paramMap;

    // JavaBean的字节码
    Class<T> clazz;

    boolean isShowLoadingDialog = true;

    public static String TAG = "NETTEST";

    private Context context;

    LoadingDialog loadingDialog;


    public HttpRequest(Context context, String path, Map<String, String> paramMap,
                       Class<T> clazz, boolean isShowLoadingDialog) {
        super();
        this.context = context;
        this.path = path;
        this.paramMap = paramMap;
        this.clazz = clazz;
        this.isShowLoadingDialog = isShowLoadingDialog;
    }

    public HttpRequest(Context context, String path, Map<String, String> paramMap,
                       Class<T> clazz) {

        this(context, path, paramMap, clazz, true);
    }

    /**
     * 构造方法
     * <p/>
     * 应用的上下文
     *
     * @param path  请求的URL
     * @param clazz 解析返回数据的实体化Bean
     */
    public HttpRequest(Context context, String path, Class<T> clazz) {

        this(context, path, null, clazz, true);
    }


    public HttpRequest(Context context, String path, Class<T> clazz,
                       boolean isShowLoadingDialog) {

        this(context, path, null, clazz, isShowLoadingDialog);
    }

    /**
     * 重新设置请求参数
     *
     * @param paramMap
     */
    public void request(Map<String, String> paramMap) {

        this.paramMap = paramMap;

        request();
    }

    /**
     * 向网络进行请求
     */
    public void request() {

        // 通用的请求参数
        paramMap.put("token", GlobalParams.token);
        paramMap.put("smemberId", GlobalParams.smemberId);

        // 如果是调试模式，打印日志
        if (BuildConfig.DEBUG) {
            // 遍历map集合
            Log.d(TAG, "请求参数:");
            for (Map.Entry entry : paramMap.entrySet()) {
                Log.d(TAG, entry.getKey() + "=" + entry.getValue());
            }
            // 下面的代码直到System.out为打印get请求的地址，方便用浏览器模拟访问，先打印出来再进行请求，给URL添加后缀
            StringBuilder builder = new StringBuilder();
            if (path.contains("?")) {
                builder.append("&");
            } else {
                builder.append("?");
            }
            if (paramMap != null) {
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    builder.append(key).append("=").append(value).append("&");
                }
            }
            String uri = ConstantValue.HOST + path + builder.toString();
            Log.d(TAG, "get请求的地址:   " + uri);
        }

        // 进行post请求，
        StringRequest request = getPostStringRequest();

        if (isShowLoadingDialog && context instanceof Activity) {
            loadingDialog = new LoadingDialog(context);
            loadingDialog.show();
        }
        if (queue == null) {
            initRequestQueue(context);
        }
        queue.add(request);
    }

    public abstract void onSuccess(T fromJson);

    public void onError(BaseJson baseJson, VolleyError error) {

    }


    /**
     * 得到POST请求后返回的所有的结果
     */
    private StringRequest getPostStringRequest() {

        StringRequest postRequest = new StringRequest(Request.Method.POST, ConstantValue.HOST
                + path, new MyResponseListener(), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.showToast("网络连接失败");

                cancelDialog();

                onError(null, error);
            }
        }) {

            /**
             * 设置参数
             */
            @Override
            protected Map<String, String> getParams() {
                return paramMap;
            }

        };

        // Set a retry policy in case of SocketTimeout & ConnectionTimeout
        // Exceptions. Volley does retry for you if you have specified the
        // policy.
        postRequest.setRetryPolicy(new DefaultRetryPolicy(HTTP_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        postRequest.setTag(path);
        postRequest.setShouldCache(true);
        return postRequest;
    }

    /**
     * 初始化volley request queue
     *
     * @param context
     */
    public static void initRequestQueue(Context context) {
        if (queue == null) {
            synchronized (HttpRequest.class) {
                if (queue == null) {
                    queue = Volley.newRequestQueue(context);
                    queue.start();
                }
            }
        }
    }

    /**
     * 停止全部请求
     */
    public static void stopRequestQueue() {
        if (queue != null) {
            synchronized (HttpRequest.class) {
                if (queue != null) {
                    queue.stop();
                    queue = null;
                }
            }
        }
    }

    class MyResponseListener implements Response.Listener<String> {

        Gson gson = new Gson();

        @Override
        public void onResponse(String response) {
            {
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "请求路径: " + path + "返回内容: " + response);
                }

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int errCode = jsonObject.getInt("errCode");

                    String errMsg = jsonObject.getString("errMsg");
                    ToastUtils.showToast(errMsg);
                    // 根据每个项目的不同，这里加一些错误的判断
                    if (errCode != 000000) {
                        // Toast.makeText(context, error_message,
                        // 0).show();
                        BaseJson baseJson = new BaseJson(errCode, errMsg);
                        HttpRequest.this.onError(baseJson, null);
                        cancelDialog();
                        return;
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                BaseJson fromJson;
                try {
                    fromJson = gson.fromJson(response, clazz);
                } catch (JsonSyntaxException e) {
                    ToastUtils.showToast("解析失败");
                    HttpRequest.this.onError(null, null);
                    e.printStackTrace();
                    cancelDialog();
                    return;
                }
                cancelDialog();
                HttpRequest.this.onSuccess((T) fromJson);


            }
        }
    }

    private void cancelDialog() {

        if (isShowLoadingDialog) {
            loadingDialog.dismiss();
        }
    }
}
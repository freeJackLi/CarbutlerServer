package com.freelxl.baselibrary.utils;

import android.os.Handler;
import android.widget.Toast;

import com.freelxl.baselibrary.config.GlobalParams;

public class ToastUtils {

    public static void showToast(final CharSequence text) {
        if ("main".equals(Thread.currentThread().getName())) {
            Toast.makeText(GlobalParams.context, text, Toast.LENGTH_SHORT).show();
        } else {
            new Handler(GlobalParams.context.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(GlobalParams.context.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static void showToast(final int resId) {
        if ("main".equals(Thread.currentThread().getName())) {
            Toast.makeText(GlobalParams.context.getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
        } else {
            new Handler(GlobalParams.context.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(GlobalParams.context.getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

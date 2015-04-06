package com.freelxl.carbutler.server.carbutlerserver.app;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.freelxl.baselibrary.config.GlobalParams;

/**
 * Created by root-pc on 2015/4/3.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GlobalParams.context = this;

        SDKInitializer.initialize(getApplicationContext());
    }
}

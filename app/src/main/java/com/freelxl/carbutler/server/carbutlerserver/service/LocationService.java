package com.freelxl.carbutler.server.carbutlerserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.freelxl.baselibrary.bean.BaseJson;
import com.freelxl.baselibrary.utils.HttpRequest;
import com.freelxl.baselibrary.utils.ToastUtils;
import com.freelxl.carbutler.server.carbutlerserver.config.ConstantValue;

import java.util.HashMap;

public class LocationService extends Service {
    public LocationService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onCreate() {

        LocationClient mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(bdLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(10000);
        option.setIsNeedAddress(true);
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        mLocClient.setLocOption(option);
        mLocClient.setDebug(true);
        mLocClient.start();
        mLocClient.requestLocation(); // 定位
    }

    BDLocationListener bdLocationListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            ToastUtils.showToast("latitude" + latitude + "longitude" + longitude);

            HashMap<String, String> paramMap = new HashMap<>();
            paramMap.put("lat", String.valueOf(latitude));
            paramMap.put("lng", String.valueOf(longitude));
            new HttpRequest<BaseJson>(getApplicationContext(), ConstantValue.updateLatLng, paramMap, BaseJson.class, false) {

                @Override
                public void onSuccess(BaseJson fromJson) {

                }

            }.request();


        }

    };
}

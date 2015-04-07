package com.freelxl.carbutler.server.carbutlerserver.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.activity.HomeActivity;
import com.freelxl.carbutler.server.carbutlerserver.view.HomeBottomButton;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {

    @ViewInject(R.id.mMapView)
    MapView mMapView;

    @ViewInject(R.id.type1)
    HomeBottomButton type1;

    @ViewInject(R.id.type2)
    HomeBottomButton type2;

    @ViewInject(R.id.type3)
    HomeBottomButton type3;

    @ViewInject(R.id.type4)
    HomeBottomButton type4;

    @ViewInject(R.id.type5)
    HomeBottomButton type5;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ViewUtils.inject(this, view);
//        title.setMiddleText();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMapView.showZoomControls(false);

        LogUtils.d("onActivityCreated");
        // 定位初始化
        LocationClient mLocClient = new LocationClient(getActivity());
        mLocClient.registerLocationListener(new BDLocationListener() {

            @Override
            public void onReceiveLocation(BDLocation location) {
                LogUtils.d("onReceiveLocation");
                // map view 销毁后不在处理新接收的位置
                if (location == null) {
                    return;
                }

                LatLng cenpt = new LatLng(location.getLatitude(), location.getLongitude());
                LogUtils.d("cenpt" + cenpt);
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(cenpt, 18);
                mMapView.getMap().setMapStatus(mapStatusUpdate);
            }

        });
        mLocClient.requestLocation();


        initBottomButton();
    }

    public void initBottomButton() {

        type1.setNumber(0);
        type2.setNumber(0);
        type3.setNumber(0);
        type4.setNumber(0);
        type5.setNumber(0);

        type1.setType("待救援");
        type2.setType("待保养");
        type3.setType("待维修");
        type4.setType("待待办");
        type5.setType("待其他");

    }

    @OnClick(R.id.bt_toggle)
    public void bt_toggle(View view) {

        if (getActivity() instanceof HomeActivity) {
            ((HomeActivity) getActivity()).toggle();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
}
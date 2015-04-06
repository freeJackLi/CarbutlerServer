package com.freelxl.carbutler.server.carbutlerserver.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.activity.MyIncomeActivity;
import com.freelxl.carbutler.server.carbutlerserver.activity.OrderActivity;
import com.freelxl.carbutler.server.carbutlerserver.activity.SettingActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ViewUtils.inject(this,view);
        return view;
    }

    @OnClick(R.id.my_order)
    public void my_order(View view){
        Intent intent = new Intent(getActivity(), OrderActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.my_income)
    public void my_income(View view){
        Intent intent = new Intent(getActivity(), MyIncomeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.setting)
    public void setting(View view){
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }


}

package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.freelxl.baselibrary.utils.BaseUtils;
import com.freelxl.carbutler.server.carbutlerserver.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class AboutActivity extends Activity {

    @ViewInject(R.id.tv_versionName)
    TextView tv_versionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ViewUtils.inject(this);
        tv_versionName.setText("v" + BaseUtils.getVersionName(this));
    }


    @OnClick(R.id.iv_back)
    public void iv_back(View view) {
        finish();
    }
}

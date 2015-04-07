package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.freelxl.baselibrary.utils.BaseUtils;
import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.view.CommonTitle;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class AboutActivity extends Activity {

    @ViewInject(R.id.tv_versionName)
    TextView tv_versionName;

    @ViewInject(R.id.title)
    CommonTitle title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ViewUtils.inject(this);
        title.setMiddleText("关于");
        tv_versionName.setText("v" + BaseUtils.getVersionName(this));
    }

}

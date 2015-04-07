package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.os.Bundle;

import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.view.CommonTitle;
import com.lidroid.xutils.view.annotation.ViewInject;

public class RegisterSubmitActivity extends Activity {

    @ViewInject(R.id.title)
    CommonTitle title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_submit);
        title.setMiddleText("申请已提交");
    }
}

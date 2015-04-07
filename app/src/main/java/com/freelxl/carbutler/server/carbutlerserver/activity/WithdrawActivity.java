package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.os.Bundle;

import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.view.CommonTitle;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class WithdrawActivity extends Activity {

    @ViewInject(R.id.title)
    CommonTitle title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ViewUtils.inject(this);
        title.setMiddleText("提现");
    }


}

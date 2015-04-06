package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.freelxl.carbutler.server.carbutlerserver.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class MyIncomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_income);
        ViewUtils.inject(this);
    }

    @OnClick(R.id.tv_withdraw)
    public void tv_withdraw(View view) {

        Intent intent = new Intent(this, WithdrawActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv_back)
    public void iv_back(View view) {
        finish();
    }

}

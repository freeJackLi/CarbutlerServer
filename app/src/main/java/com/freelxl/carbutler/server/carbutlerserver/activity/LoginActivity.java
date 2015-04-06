package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.freelxl.baselibrary.utils.IntentUtils;
import com.freelxl.carbutler.server.carbutlerserver.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class LoginActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);

    }

    @OnClick(R.id.bt_register)
    public void bt_register(View view) {

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_callPhone)
    public void tv_callPhone(View view) {
        String phone = ((TextView) view).getText().toString();
        IntentUtils.callPhone(this, phone);
    }

    @OnClick(R.id.bt_login)
    public void bt_login(View view) {

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}

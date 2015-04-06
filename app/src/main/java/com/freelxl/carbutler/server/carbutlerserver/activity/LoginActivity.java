package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.freelxl.baselibrary.utils.IntentUtils;
import com.freelxl.baselibrary.utils.MD5Util;
import com.freelxl.carbutler.server.carbutlerserver.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;

public class LoginActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);

//        PushManager.startWork(this,
//                PushConstants.LOGIN_TYPE_API_KEY,
//                PushUtils.getMetaValue(this, "4Bac80ruzeGfTXEVne2p5oGM"));

        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "4Bac80ruzeGfTXEVne2p5oGM");
        String a = MD5Util.getMD5Str("3");

        String b = MD5Util.getMD5Str("3A");
//        System.out.print("tag----" + a + "+++++" + b);
        Log.d("3A", "-----------" + a + "--------" + b);
        ArrayList<String> strings = new ArrayList<>();
        strings.add(a);
        strings.add(b);
        PushManager.setTags(this, strings);

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

package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.freelxl.baselibrary.config.GlobalParams;
import com.freelxl.baselibrary.utils.HttpRequest;
import com.freelxl.baselibrary.utils.IntentUtils;
import com.freelxl.baselibrary.utils.ToastUtils;
import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.config.ConstantValue;
import com.freelxl.carbutler.server.carbutlerserver.domain.Login;
import com.freelxl.carbutler.server.carbutlerserver.service.LocationService;
import com.freelxl.carbutler.server.carbutlerserver.view.CommonTitle;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.HashMap;

public class LoginActivity extends Activity {

    @ViewInject(R.id.title)
    CommonTitle title;

    @ViewInject(R.id.et_loginName)
    EditText et_loginName;

    @ViewInject(R.id.et_loginPwd)
    EditText et_loginPwd;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
        title.setMiddleText("叮咚车管家服务版");
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        String loginName = sp.getString("loginName", "");
        String loginPwd = sp.getString("loginPwd", "");
        et_loginName.setText(loginName);
        et_loginPwd.setText(loginPwd);

//        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "4Bac80ruzeGfTXEVne2p5oGM");
//        String a = MD5Util.getMD5Str("3");
//
//        String b = MD5Util.getMD5Str("3A");
////        System.out.print("tag----" + a + "+++++" + b);
//        Log.d("3A", "-----------" + a + "--------" + b);
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add(a);
//        strings.add(b);
//        PushManager.setTags(this, strings);

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
        final String loginName = et_loginName.getText().toString();
        final String loginPwd = et_loginPwd.getText().toString();
        if (TextUtils.isEmpty(loginName)) {
            ToastUtils.showToast("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(loginPwd)) {
            ToastUtils.showToast("请输入密码");
            return;
        }
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("loginName", loginName);
        paramMap.put("loginPwd", loginPwd);
        new HttpRequest<Login>(LoginActivity.this
                , ConstantValue.login, paramMap, Login.class) {

            @Override
            public void onSuccess(Login fromJson) {

                SharedPreferences.Editor edit = sp.edit();
                edit.putString("loginName", loginName);
                edit.putString("loginPwd", loginPwd);
                edit.commit();
                
                startService(new Intent(LoginActivity.this, LocationService.class));
                GlobalParams.token = fromJson.data.token;
                GlobalParams.smemberId = fromJson.data.smember.smemberId;

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }

        }.request();

    }

}

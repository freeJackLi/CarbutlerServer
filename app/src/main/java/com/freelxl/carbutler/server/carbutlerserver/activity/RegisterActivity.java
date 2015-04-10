package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.freelxl.baselibrary.bean.BaseJson;
import com.freelxl.baselibrary.utils.HttpRequest;
import com.freelxl.baselibrary.utils.ToastUtils;
import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.config.ConstantValue;
import com.freelxl.carbutler.server.carbutlerserver.view.CommonTitle;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.HashMap;

public class RegisterActivity extends Activity {

    @ViewInject(R.id.title)
    CommonTitle title;

    @ViewInject(R.id.et_smemberName)
    EditText et_smemberName;

    @ViewInject(R.id.et_smemberMobile)
    EditText et_smemberMobile;

    @ViewInject(R.id.cb_bao_yang)
    CheckBox cb_bao_yang;

    @ViewInject(R.id.cb_wei_xiu)
    CheckBox cb_wei_xiu;

    @ViewInject(R.id.cb_che_zhuang_le)
    CheckBox cb_che_zhuang_le;

    @ViewInject(R.id.cb_gua_ceng)
    CheckBox cb_gua_ceng;

    @ViewInject(R.id.cb_da_bu_zhao_huo)
    CheckBox cb_da_bu_zhao_huo;

    @ViewInject(R.id.cb_lun_tai_mei_qi)
    CheckBox cb_lun_tai_mei_qi;

    @ViewInject(R.id.et_smemberExp)
    EditText et_smemberExp;

    String smemberBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ViewUtils.inject(this);
        title.setMiddleText("申请成为技师");
    }

    @OnClick(R.id.bt_register)
    public void bt_register(View view) {
        String smemberName = et_smemberName.getText().toString();
        String smemberMobile = et_smemberMobile.getText().toString();

        if (TextUtils.isEmpty(smemberName)) {
            ToastUtils.showToast("请填写姓名");
            return;
        }
        if (TextUtils.isEmpty(smemberMobile)) {
            ToastUtils.showToast("请填写手机号");
            return;
        }

        StringBuilder smemberServiceBuilder = new StringBuilder("");
        if (cb_bao_yang.isChecked()) {
            smemberServiceBuilder.append("保养,");
        }
        if (cb_wei_xiu.isChecked()) {
            smemberServiceBuilder.append("维修,");
        }
        if (cb_che_zhuang_le.isChecked()) {
            smemberServiceBuilder.append("车撞了,");
        }
        if (cb_gua_ceng.isChecked()) {
            smemberServiceBuilder.append("剐蹭,");
        }
        if (cb_da_bu_zhao_huo.isChecked()) {
            smemberServiceBuilder.append("打不着火,");
        }
        if (cb_lun_tai_mei_qi.isChecked()) {
            smemberServiceBuilder.append("轮胎没气,");
        }
        //去掉最后的空格
        int lastIndexOf = smemberServiceBuilder.lastIndexOf(",");
        if (lastIndexOf != -1) {
            smemberServiceBuilder.substring(0, lastIndexOf - 1);
        }
        String smemberService = smemberServiceBuilder.toString();
        if (TextUtils.isEmpty(smemberService)) {
            ToastUtils.showToast("请选择擅长服务");
            return;
        }

        String smemberExp = et_smemberExp.getText().toString();
        if (TextUtils.isEmpty(smemberExp)) {
            ToastUtils.showToast("请填写经验年限");
            return;
        }


        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("smemberName", smemberName);
        paramMap.put("smemberMobile", smemberMobile);
        //TODO 完成选择品牌
        paramMap.put("smemberBrand", "1000000001,1000000002,1000000004,5D18A5F17A");
        paramMap.put("smemberService", smemberService);
        paramMap.put("smemberExp", smemberExp);

        new HttpRequest<BaseJson>(RegisterActivity.this
                , ConstantValue.register, paramMap, BaseJson.class) {

            @Override
            public void onSuccess(BaseJson fromJson) {
                Intent intent = new Intent(RegisterActivity.this, RegisterSubmitActivity.class);
                startActivity(intent);
                finish();
            }

        }.request();

    }

    @OnClick(R.id.ll_smemberBrand)
    public void ll_smemberBrand(View view) {
        Intent intent = new Intent(this, GoodAtBrandActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            smemberBrand = data.getStringExtra("smemberBrand");
        }
    }
}

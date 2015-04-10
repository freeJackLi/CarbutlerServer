package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.freelxl.baselibrary.bean.BaseJson;
import com.freelxl.baselibrary.utils.HttpRequest;
import com.freelxl.baselibrary.utils.ToastUtils;
import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.config.ConstantValue;
import com.freelxl.carbutler.server.carbutlerserver.view.CommonTitle;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.HashMap;

public class FeedbackActivity extends Activity {

    @ViewInject(R.id.title)
    CommonTitle title;

    @ViewInject(R.id.et_opinionContent)
    EditText et_opinionContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ViewUtils.inject(this);
        title.setMiddleText("意见反馈");
        title.setRightText("提交反馈");

        title.setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opinionContent = et_opinionContent.getText().toString();
                if (TextUtils.isEmpty(opinionContent)) {
                    ToastUtils.showToast("请填写意见内容");
                    return;
                }
                HashMap<String, String> paramMap = new HashMap<>();
                paramMap.put("opinionSource", "2");
                paramMap.put("opinionClientType", "2");
                paramMap.put("opinionContent", opinionContent);

                new HttpRequest<BaseJson>(FeedbackActivity.this
                        , ConstantValue.addOpinion, paramMap, BaseJson.class) {

                    @Override
                    public void onSuccess(BaseJson fromJson) {
                        FeedbackActivity.this.finish();
                    }

                }.request();
            }
        });
    }

}

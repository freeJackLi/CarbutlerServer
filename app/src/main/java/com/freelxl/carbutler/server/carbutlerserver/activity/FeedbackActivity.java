package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.view.CommonTitle;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class FeedbackActivity extends Activity {

    @ViewInject(R.id.title)
    CommonTitle title;

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

            }
        });
    }

}

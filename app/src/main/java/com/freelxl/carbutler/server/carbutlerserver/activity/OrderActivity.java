package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.freelxl.baselibrary.utils.ToastUtils;
import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.dialog.WheelTimePickerDialog;
import com.freelxl.carbutler.server.carbutlerserver.view.CommonTitle;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.Arrays;
import java.util.List;

public class OrderActivity extends Activity implements WheelTimePickerDialog.OnTimeSetListener {

    @ViewInject(R.id.title)
    CommonTitle title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ViewUtils.inject(this);
        title.setMiddleText("我的订单");
    }

    @OnClick(R.id.timepicker)
    public void timepicker(View view) {

        WheelTimePickerDialog wheelTimePickerDialog = new WheelTimePickerDialog(this);
        wheelTimePickerDialog.setOnTimeSetListener(this);
        wheelTimePickerDialog.show();
    }

    @OnClick(R.id.photo)
    public void photo(View view) {

        Spinner spinner = new Spinner(this);
        spinner.setPrompt("上传方式");// 设置Prompt

        List age_data = Arrays.asList(new CharSequence[]{"相机选取", "手机拍照", "取消"});
        ArrayAdapter ages = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, age_data);
        ages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// 设置列表项显示风格
        spinner.setAdapter(ages);// 设置显示信息

        spinner.performClick();
    }

    @Override
    public void onTimeSet(int year, int month, int day, int hourOfDay, int minute) {

        ToastUtils.showToast("year" + year + "month" + month + "day" + day + "hourOfDay" + hourOfDay + "minute" + minute);
    }
}

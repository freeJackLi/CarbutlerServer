package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.freelxl.baselibrary.utils.HttpRequest;
import com.freelxl.carbutler.server.carbutlerserver.adapter.CarBrandAdapter;
import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.config.ConstantValue;
import com.freelxl.carbutler.server.carbutlerserver.domain.QueryAllBrand;
import com.freelxl.carbutler.server.carbutlerserver.view.CommonTitle;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.HashMap;

public class GoodAtBrandActivity extends Activity {

    @ViewInject(R.id.title)
    CommonTitle title;

    @ViewInject(R.id.lv_car_brand)
    ListView lv_car_brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_at_brand);
        ViewUtils.inject(this);
        title.setMiddleText("擅长品牌");
        title.setRightText("保存");


        title.setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("smemberBrand", "smemberBrand");
                setResult(0, intent);
                finish();
            }
        });

        fillData();
    }

    private void fillData() {
        HashMap<String, String> paramMap = new HashMap<>();
        new HttpRequest<QueryAllBrand>(GoodAtBrandActivity.this
                , ConstantValue.queryAllBrand, paramMap, QueryAllBrand.class) {

            @Override
            public void onSuccess(QueryAllBrand fromJson) {
                lv_car_brand.setAdapter(new CarBrandAdapter(GoodAtBrandActivity.this, fromJson.data));
            }

        }.request();
    }


}

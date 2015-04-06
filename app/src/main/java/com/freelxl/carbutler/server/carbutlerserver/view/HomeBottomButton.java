package com.freelxl.carbutler.server.carbutlerserver.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freelxl.carbutler.server.carbutlerserver.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by root-pc on 2015/4/5.
 */
public class HomeBottomButton extends RelativeLayout {

    @ViewInject(R.id.tv_number)
    TextView tv_number;

    @ViewInject(R.id.tv_type)
    TextView tv_type;

    public HomeBottomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewUtils.inject(this);
    }

    public void setNumber(int number) {
        tv_number.setText(String.valueOf(number));
    }

    public int getNumber() {
        String str = tv_number.getText().toString();
        int number = Integer.valueOf(str);
        return number;
    }

    public void setType(String type){
        tv_type.setText(type);
    }

    public String getType(){

        return tv_type.getText().toString();
    }
}

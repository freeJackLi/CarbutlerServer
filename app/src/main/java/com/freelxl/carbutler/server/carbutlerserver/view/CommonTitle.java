package com.freelxl.carbutler.server.carbutlerserver.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freelxl.carbutler.server.carbutlerserver.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * Created by root-pc on 2015/4/7.
 */
public class CommonTitle extends RelativeLayout {

    Context context;

    OnClickListener onRightTextClickListener;

    OnClickListener onBackClickListener;

    @ViewInject(R.id.iv_back)
    ImageView iv_back;

    @ViewInject(R.id.rightText)
    TextView rightText;

    @ViewInject(R.id.middleText)
    TextView middleText;


    public CommonTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewUtils.inject(this);
    }

    @OnClick(R.id.iv_back)
    public void iv_back(View view) {
        if (onBackClickListener != null) {
            onBackClickListener.onClick(view);
        }
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    @OnClick(R.id.rightText)
    public void rightText(View view) {
        if (onRightTextClickListener != null) {
            onRightTextClickListener.onClick(view);
        }
    }

    public void setRightText(String str) {

        rightText.setVisibility(View.VISIBLE);
        rightText.setText(str);
    }

    public void setMiddleText(String str) {
        middleText.setText(str);
    }

    public void setOnRightTextClickListener(OnClickListener onClickListener) {
        this.onRightTextClickListener = onClickListener;
    }

    public void setOnBackClickListener(OnClickListener onClickListener) {
        this.onBackClickListener = onClickListener;
    }


}

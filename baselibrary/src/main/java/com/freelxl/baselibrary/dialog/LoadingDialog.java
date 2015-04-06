package com.freelxl.baselibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import com.freelxl.baselibrary.R;

/**
 * Created by root-pc on 2015/4/3.
 */
public class LoadingDialog extends Dialog {

    public static final String LOADING_TAG = "loading_tag";

    public LoadingDialog(Context context) {
        super(context, R.style.DialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.R.color.transparent));
    }


}

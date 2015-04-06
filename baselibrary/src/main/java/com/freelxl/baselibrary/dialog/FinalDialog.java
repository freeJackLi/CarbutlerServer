package com.freelxl.baselibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.freelxl.baselibrary.R;

public class FinalDialog extends Dialog {

    Button bt_positive;

    Button bt_negative;

    String positiveName;
    String negativeName;


    View view;

    public FinalDialog(Context context) {

        this(context, "确定", "取消");
    }

    public FinalDialog(Context context, String positiveName, String negativeName) {

        super(context, R.style.DialogStyle);
        this.positiveName = positiveName;
        this.negativeName = negativeName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_final);
        bt_positive = (Button) view.findViewById(R.id.bt_positive);
        bt_negative = (Button) view.findViewById(R.id.bt_negative);

        if (positiveName == null) {
            bt_positive.setVisibility(View.GONE);
        } else {
            bt_positive.setVisibility(View.VISIBLE);
            bt_positive.setText(positiveName);
        }
        if (negativeName == null) {
            bt_negative.setVisibility(View.GONE);
        } else {
            bt_negative.setVisibility(View.VISIBLE);
            bt_negative.setText(negativeName);
        }

        bt_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinalDialog.this.dismiss();
            }
        });
    }

    public void setOnPositiveClickListener(View.OnClickListener listener) {
        bt_positive.setOnClickListener(listener);
    }

    public void setOnNegativeClickListener(View.OnClickListener listener) {
        bt_negative.setOnClickListener(listener);
    }
}

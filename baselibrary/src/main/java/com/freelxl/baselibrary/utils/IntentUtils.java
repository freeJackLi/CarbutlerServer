package com.freelxl.baselibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by root-pc on 2015/4/3.
 */
public class IntentUtils {

    /*
     用intent启动拨打电话
     */
    public static void callPhone(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}

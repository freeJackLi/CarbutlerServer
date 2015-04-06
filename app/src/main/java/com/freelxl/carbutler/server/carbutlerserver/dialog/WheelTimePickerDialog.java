package com.freelxl.carbutler.server.carbutlerserver.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.freelxl.carbutler.server.carbutlerserver.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.Calendar;

import kankan.wheel.widget.WheelUtils;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.DateArrayAdapter;

/**
 * Created by root-pc on 2015/4/4.
 */
public class WheelTimePickerDialog extends Dialog {

    OnTimeSetListener mOnTimeSetListener;
    Context context;

    public WheelTimePickerDialog(Context context) {

        super(context, com.freelxl.baselibrary.R.style.DialogStyle);
        this.context = context;
    }


    WheelView day;
    WheelView hourOfDay;
    WheelView minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.dialog_wheel_time_picker, null);
        setContentView(view);
        ViewUtils.inject(this, view);


        Calendar calendar = Calendar.getInstance();
        day = (WheelView) findViewById(R.id.day);
        hourOfDay = (WheelView) findViewById(R.id.hourOfDay);
        minute = (WheelView) findViewById(R.id.minute);

        WheelUtils.initWheelView(day);
        WheelUtils.initWheelView(hourOfDay);
        WheelUtils.initWheelView(minute);

        int month1 = calendar.get(Calendar.MONTH) + 1;
        int day1 = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, 1);
        int month2 = calendar.get(Calendar.MONTH) + 1;
        int day2 = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, 1);
        int month3 = calendar.get(Calendar.MONTH) + 1;
        int day3 = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, 1);

        String[] days = new String[]{month1 + "." + day1, month2 + "." + day2, month3 + "." + day3};
        day.setViewAdapter(new DateArrayAdapter(context, days, 0));

        //hourOfDay
        String[] hourOfDays = new String[24];
        for (int i = 0; i < hourOfDays.length; i++) {
            hourOfDays[i] = i + "时";
        }
        hourOfDay.setViewAdapter(new DateArrayAdapter(context, hourOfDays, 0));
        int hour_of_day = calendar.get(Calendar.HOUR_OF_DAY);
        hourOfDay.setCurrentItem(hour_of_day);

        //minute
        String minutes[] = new String[]{"00分", "30分"};
        minute.setViewAdapter(new DateArrayAdapter(context, minutes, 0));

    }


    public void setOnTimeSetListener(OnTimeSetListener onTimeSetListener) {
        this.mOnTimeSetListener = onTimeSetListener;
    }

    public interface OnTimeSetListener {

        void onTimeSet(int year, int month, int day, int hourOfDay, int minute);
    }

    @OnClick(R.id.bt_positive)
    public void bt_positive(View view) {
        if (mOnTimeSetListener != null) {
            int dayCurrentItem = day.getCurrentItem();
            int hourOfDayCurrentItem = hourOfDay.getCurrentItem();
            int minuteCurrentItem = minute.getCurrentItem();

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, dayCurrentItem);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DATE);
            mOnTimeSetListener.onTimeSet(year, month + 1, day, hourOfDayCurrentItem, minuteCurrentItem);
        }
        this.dismiss();
    }

    @OnClick(R.id.bt_negative)
    public void bt_negative(View view) {

        this.dismiss();
    }
}

package com.freelxl.baselibrary.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class DisplayUtil {

	/**
	 * 设置ListView 的Item高度
	 * 
	 * @param activity
	 * @param listView
	 * @param height
	 */
	public static void setListViewItemHeight(Activity activity,
			ListView listView, int height) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int itemNum = listAdapter.getCount();

		for (int j = 0; j < itemNum; j++) {
			View listItem = listAdapter.getView(j, null, listView);
			LayoutParams params = listItem.getLayoutParams();
			params.height = height;
			listItem.setLayoutParams(params);
		}
	}

	/**
	 * 得到屏幕的高度(通知栏除外)
	 * 
	 * @param activity
	 * @return
	 */
	public static int getContentViewHeight(Activity activity) {
		int screenHeight = DisplayUtil.getScreenHeight(activity);
		return screenHeight - getStatusBarHeight(activity);
	}

	public static int getViewHeight(View view) {

		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		int height = view.getMeasuredHeight();
		return height;
	}

	public static int getViewWidth(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		int width = view.getMeasuredWidth();
		return width;
	}

	/**
	 * 得到通知栏的高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}

	/**
	 * 得到屏幕高度
	 * 
	 * @param activity
	 * @return
	 */
	public static int getScreenHeight(Activity activity) {

		return activity.getWindowManager().getDefaultDisplay().getHeight();
	}

	/**
	 * 得到屏幕宽度
	 * 
	 * @param activity
	 * @return
	 */
	public static int getScreenWight(Activity activity) {

		return activity.getWindowManager().getDefaultDisplay().getWidth();
	}

	/**
	 * 
	 * @param activity
	 * @param view
	 *            要设置的控件
	 * @param height
	 *            控件占屏幕高的比率
	 * @param width
	 *            控件占屏幕宽的比率
	 */
	public static void setViewHWLocation(Activity activity, View view,
			float heightRatio, float widthRatio, float heightLocation,
			float widthLocation) {

		int screenWidth = activity.getWindowManager().getDefaultDisplay()
				.getWidth();
		int screenHeight = activity.getWindowManager().getDefaultDisplay()
				.getHeight();
		LayoutParams lParams = view.getLayoutParams();
		LayoutParams layoutParams = view.getLayoutParams();
		layoutParams.width = (int) (screenWidth * widthRatio);
		layoutParams.height = (int) (screenHeight * heightRatio);
		view.setLayoutParams(lParams);
		view.setX(screenWidth * widthLocation - layoutParams.width / 2);
		view.setY(screenHeight * heightLocation - layoutParams.height / 2);
	}

	public static void setTextSize(Activity activity, TextView textView,
			float sizeRatio) {
		// textView.setTextSize();

	}

	public static void setViewHeight(Activity activity, View view,
			float heightRatio) {

		int screenHeight = activity.getWindowManager().getDefaultDisplay()
				.getHeight();
		LayoutParams lParams = view.getLayoutParams();
		LayoutParams layoutParams = view.getLayoutParams();
		layoutParams.height = (int) (screenHeight * heightRatio);
		view.setLayoutParams(lParams);
	}

	/**
	 * 注意：设置dialog控件宽高一定要放在dialog.show()之后！ 设置dialog显示的大小
	 * 
	 * @param activity
	 * @param dialog
	 *            要设置对话框
	 * @param height占屏幕高的比例
	 * @param width占屏幕宽的比例
	 */
	public static void setDialogHW(Activity activity, Dialog dialog,
			float heightRatio, float widthRatio) {
		WindowManager m = activity.getWindowManager();
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值
		p.height = (int) (d.getHeight() * heightRatio);
		p.width = (int) (d.getWidth() * widthRatio);
		dialog.onWindowAttributesChanged(p);
		dialog.getWindow().setAttributes(p);
	}

	/**
	 * 设置dialog的位置
	 * 
	 * @param activity
	 * @param dialog
	 * @param heightLocation
	 *            相对原来位置的偏移量
	 * @param widthLocation
	 *            相对原来位置的偏移量
	 */
	public static void setDialogLocation(Activity activity, Dialog dialog,
			float heightLocation, float widthLocation) {
		WindowManager m = activity.getWindowManager();
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值
		p.x = (int) (d.getWidth() * widthLocation);
		p.y = (int) (d.getHeight() * heightLocation);
		dialog.getWindow().setAttributes(p);
	}

	/**
	 * 设置dialog的位置
	 * 
	 * @param activity
	 * @param dialog
	 * @param heightLocation
	 *            相对原来位置的偏移量
	 * @param widthLocation
	 *            相对原来位置的偏移量
	 */
	public static void context(Activity activity, Dialog dialog,
			float heightLocation, float widthLocation) {
		WindowManager m = activity.getWindowManager();
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值
		p.x = (int) (d.getWidth() * widthLocation);
		p.y = (int) (d.getHeight() * heightLocation);
		dialog.getWindow().setAttributes(p);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * density + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / density + 0.5f);
	}

	public static float getDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}
}

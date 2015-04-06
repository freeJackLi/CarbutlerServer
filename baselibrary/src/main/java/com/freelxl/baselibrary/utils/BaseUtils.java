package com.freelxl.baselibrary.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

public class BaseUtils {

	private static String versionName;

	private static int versionCode;

	/**
	 * 
	 * @param context
	 * @return 如果没有获取成功则返回-1
	 */
	public static int getVersionCode(Context context) {
		PackageInfo info = null;
		if (versionCode <= 0) {
			try {
				info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			} catch (NameNotFoundException e) {
			}
			versionCode = info == null ? -1 : info.versionCode;
		}
		return versionCode;
	}

	/**
	 * 取得版本号
	 * 
	 * @param context
	 * @return versionName OR Unknown
	 */
	public static String getVersionName(Context context) {
		if (versionName == null) {
			try {
				PackageInfo manager = context.getPackageManager().getPackageInfo(
						context.getPackageName(), 0);
				versionName = manager.versionName;
			} catch (NameNotFoundException e) {
				versionName = "Unknown";
			}
		}
		return versionName;
	}

	/**
	 * 判断网络是否连接
	 * 
	 * @param context
	 * @return true 连接
	 */
	public static boolean isNetWorkAvailable(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = conn.getActiveNetworkInfo();
		return (info != null && info.isConnected());
	}

	/**
	 * 
	 * @param context
	 * @param runnable
	 */
	public static void runOnUiThread(Context context, Runnable runnable) {
		new Handler(context.getMainLooper()).post(runnable);
	}
}

package com.freelxl.baselibrary.versionUpdate;

import java.io.File;

import com.freelxl.baselibrary.BuildConfig;
import com.freelxl.baselibrary.R;
import com.freelxl.baselibrary.versionUpdate.DownLoadManager.DownLoadListener;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * 使用方法：可以直接添加以下代码完成版本更新，传入extra ： url
 * Intent intent = new Intent(getActivity(), UpdateService.class);
 * intent.putExtra(UpdateService.URL, apkurl);
 * getActivity().startService(intent); ToastUtils.showToast(getActivity(),
 * "正在下载...", 0);
 * 
 * @author root-pc
 * 
 */
public class UpdateService extends Service {

	public static final String URL = "url";
	private NotificationCompat.Builder builder;
	private Handler handler;
	private NotificationManager notificationManager;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent == null) {
			return super.onStartCommand(intent, flags, startId);
		}
		String action = intent.getAction();
		if (!TextUtils.isEmpty(action)) {
			Toast.makeText(getApplicationContext(), "action is null", 0).show();
		} else {
			String url = intent.getStringExtra(URL);
			if (TextUtils.isEmpty(url)) {
				if (BuildConfig.DEBUG) {
					throw new RuntimeException("获取APK更新地址失败");
				}
				return super.onStartCommand(intent, flags, startId);
			} else {
				startUpdate(url);
			}
			handler = new Handler(getMainLooper()) {
				@Override
				public void handleMessage(Message msg) {
					if (builder != null) {
						switch (msg.what) {
						case 1:
							builder.setProgress(100, (Integer) msg.obj, false);
							builder.setContentText((Integer) msg.obj + "%");
							notificationManager
									.notify(R.id.update_notification_id, builder.build());
							break;
						case 2:
							// builder.setProgress(100, 100, false);
							// builder.setContentText("100%").setContentTitle("下载完成，点击安装");
							// Intent intent = new Intent();
							// intent.setAction(Intent.ACTION_VIEW);
							// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							// intent.setDataAndType(Uri.fromFile(new
							// File(getApkPath())),
							// "application/vnd.android.package-archive");
							// PendingIntent pendingIntent =
							// PendingIntent.getActivity(UpdateService.this, 0,
							// intent, PendingIntent.FLAG_UPDATE_CURRENT);
							// builder.setAutoCancel(true);
							// builder.setContentIntent(pendingIntent);
							// notificationManager.notify(R.id.notification_id,
							// builder.build());
							notificationManager.cancel(R.id.update_notification_id);
							Intent intent = new Intent();
							intent.setAction(Intent.ACTION_VIEW);
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.setDataAndType(Uri.fromFile(new File(getApkPath())),
									"application/vnd.android.package-archive");
							startActivity(intent);
							break;
						}
					}
				}
			};
		}

		return super.onStartCommand(intent, flags, startId);
	}

	private void startUpdate(String url) {
		createNotification();
		// startDownLoad(JinritemaiApplication.getApiDomain().endsWith("/") ?
		// JinritemaiApplication.getApiDomain()
		// .substring(0, JinritemaiApplication.getApiDomain().length() - 1) :
		// JinritemaiApplication.getApiDomain()
		// + (url.startsWith("/") ? url : ("/" + url)));
		startDownLoad(url);
	}

	private void createNotification() {
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(R.id.update_notification_id);
		builder = new NotificationCompat.Builder(getApplicationContext());
		builder.setSmallIcon(R.drawable.ic_launcher).setContentTitle("正在下载 ...")
				.setContentText("0%");
		builder.setPriority(2 | Notification.DEFAULT_ALL);
		builder.setProgress(100, 0, false);
		builder.setOnlyAlertOnce(true);
		builder.setOngoing(true);
		notificationManager.notify(R.id.update_notification_id, builder.build());
	}

	private void startDownLoad(String url) {
		// LogUtils.i("startDownLoad", "url:" + url);
		DownLoadManager.getInstance().downLoad(url, new DownLoadListener() {

			@Override
			public void onStartLoading(long totalSize) {
				// LogUtils.i("startDownLoad", "onStartLoading:" + totalSize);
			}

			@Override
			public void onLoading(long currentSize, float percent, float speed) {
				Message msg = Message.obtain();
				msg.what = 1;
				msg.obj = (int) (percent * 100);
				// LogUtils.i("startDownLoad", "onLoading:" + msg.obj);
				handler.sendMessage(msg);
			}

			@Override
			public void onLoadingFinish(long totalSize) {
				// LogUtils.i("startDownLoad", "onLoadingFinish:" + totalSize);
				Message msg = Message.obtain();
				msg.what = 2;
				handler.sendMessage(msg);
			}

			@Override
			public void onFailure(String error) {
				// LogUtils.i("startDownLoad", "onFailure:" + error);
			}
		}, new File(getApkPath()));
	}

	private String getApkPath() {
		return getAppRootDir() + File.separator + R.string.app_name + ".apk";
	}

	/**
	 * 获取sdcard的绝对路径
	 * 
	 * @return
	 */
	public String getSDcardDir() {
		String sdcardPath = null;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {
			sdcardPath = getApplicationContext().getCacheDir().getAbsolutePath();
		}

		return sdcardPath;
	}

	/**
	 * 获取应用跟目录
	 * 
	 * @return
	 */
	public String getAppRootDir() {
		String appRootDir = getSDcardDir() + File.separator + "zroom";
		File file = new File(appRootDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return appRootDir;
	}
}

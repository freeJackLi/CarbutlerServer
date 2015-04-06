package com.freelxl.baselibrary.versionUpdate;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DownLoadManager {
	private static final String MAIN = "main";
	private static DownLoadManager instance = new DownLoadManager();

	public static DownLoadManager getInstance() {
		return instance;
	}

	public void downLoad(final String uri, final DownLoadListener listener,
			final File targetFile) {
		if (MAIN.equalsIgnoreCase(Thread.currentThread().getName())) {
			new Thread() {
				public void run() {
					downloadNewThread(uri, listener, targetFile);
				};
			}.start();
		} else {
			downloadNewThread(uri, listener, targetFile);
		}
	}

	private void downloadNewThread(String uri, DownLoadListener listener,
			File targetFile) {
		HttpGet get = new HttpGet(uri);
		HttpClient client = new DefaultHttpClient();
		// 请求超时
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 60 * 1000);
		// 读取超时
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				60 * 1000);
		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				long totalSize = entity.getContentLength();
				float tempPercent = 0.005f / totalSize * (5 * 1024 * 1024);
				if (listener != null) {
					listener.onStartLoading(totalSize);
				}
				File parent = targetFile.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs();
				} else if (!parent.isDirectory()) {
					if (parent.delete()) {
						parent.mkdirs();
					}
				}
				OutputStream os = new FileOutputStream(targetFile);
				InputStream is = entity.getContent();
				long currentSize = 0;
				byte[] buffer = new byte[1024];
				int len = -1;
				long currentTime = System.currentTimeMillis();
				long tempSize = 0;
				while ((len = is.read(buffer)) != -1) {
					os.write(buffer, 0, len);
					currentSize += len;
					tempSize += len;
					if (((float) tempSize) / totalSize >= tempPercent
							|| currentSize == totalSize) {
						if (listener != null) {
							listener.onLoading(currentSize,
									((float) currentSize) / totalSize,
									((float) len) / System.currentTimeMillis()
											- currentTime);
						}
						tempSize = 0;
						currentTime = System.currentTimeMillis();
					}
				}
				os.close();
				is.close();
				if (listener != null) {
					if (currentSize == totalSize) {
						listener.onLoadingFinish(currentSize);
					} else {
						listener.onFailure("文件大小不对");
					}
				}
			} else {
				if (listener != null) {
					listener.onFailure(response.getStatusLine().getStatusCode()
							+ "");
				}
			}
		} catch (Exception e) {
			if (listener != null) {
				listener.onFailure(e.toString());
			}
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}
	}

	public interface DownLoadListener {
		void onStartLoading(long totalSize);

		/**
		 * 
		 * @param currentSize
		 *            byte
		 * @param percent
		 * @param speed
		 *            byte/second
		 */
		void onLoading(long currentSize, float percent, float speed);

		void onLoadingFinish(long totalSize);

		void onFailure(String error);
	}
}

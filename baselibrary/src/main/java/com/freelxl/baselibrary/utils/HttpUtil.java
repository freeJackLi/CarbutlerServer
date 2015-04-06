package com.freelxl.baselibrary.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 联网
 * 
 */
public class HttpUtil {
	/**
	 * 获得指定文件的byte数组
	 */
	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	/**
	 * 上传图片到服务器
	 * 
	 * @param URL
	 * @param bytes
	 * @param filename
	 * @return
	 */
	public static String httpUploadTo(String URL, byte[] bytes, String filename) {
		String text = null;
		try {
			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// 发送POST请求必须设置如下两行 ,请求头
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");

			conn.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=---------------------------------0xKhTmLbOuNdArY");
			conn.setConnectTimeout(10000);
			conn.connect();
			OutputStream out = conn.getOutputStream();

			// 请求体 Postbody
			StringBuilder sb = new StringBuilder();
			sb.append("-----------------------------------0xKhTmLbOuNdArY\r\n");
			sb.append("Content-Disposition: form-data; name=\"iosImage\"; filename=\"" + filename + "\"\r\n");
			sb.append("Content-Type: image/jpeg\r\n\r\n");
			out.write(sb.toString().getBytes("utf-8"));
			out.write(bytes);
			out.write("\r\n-----------------------------------0xKhTmLbOuNdArY--\r\n".getBytes("utf-8"));
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (text != null) {
					text += line;
				} else {
					text = line;
				}
			}
			conn.disconnect();
		} catch (Exception e) {
			System.out.println("发送文件出现异常！" + e);
			e.printStackTrace();
		}
		return text;
	}
}

package com.freelxl.baselibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.freelxl.baselibrary.R;
import com.lidroid.xutils.util.LogUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class MyImageLoader {
	private static MyImageLoader instance = new MyImageLoader();
	private DisplayImageOptions options;

	public static MyImageLoader getInstance() {
		return instance;
	}

	private DisplayImageOptions getDefaultDisplayImageOptions() {
		if (options == null)
			options = new DisplayImageOptions.Builder()
//			 .showImageOnLoading(R.drawable.ic_default_big) // 设置图片下载期间显示的图片
					.showImageForEmptyUri(R.drawable.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
					.showImageOnFail(R.drawable.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
					.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
					.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
					.resetViewBeforeLoading(true) // 设置图片在下载前是否重置，复位
					.bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.IN_SAMPLE_INT)
					// .displayer(new FadeInBitmapDisplayer(300))//
					// 是否图片加载好后渐入的动画时间
					.build();
		return options;
	}

	private ImageLoader getImageLoader(Context context) {
		if (!ImageLoader.getInstance().isInited()) {
			ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
		}
		return ImageLoader.getInstance();
	}

	public void loadImage(String url, ImageView imageView, ImageLoadingListener listener) {
		getImageLoader(imageView.getContext()).displayImage(url, imageView, getDefaultDisplayImageOptions(), listener);
	}

	public void loadImage(String url, ImageView imageView) {
		getImageLoader(imageView.getContext()).displayImage(url, imageView, getDefaultDisplayImageOptions(), new ImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				LogUtils.i("onLoadingStarted:" + imageUri);
			}
			
			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				LogUtils.i("onLoadingFailed:" + imageUri);
			}
			
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				LogUtils.i("onLoadingComplete:" + imageUri);
			}
			
			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				LogUtils.i("onLoadingCancelled:" + imageUri);
			}
		});
	}
}

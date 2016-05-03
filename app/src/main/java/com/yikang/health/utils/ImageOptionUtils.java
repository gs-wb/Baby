package com.yikang.health.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class ImageOptionUtils {

	
	public static DisplayImageOptions getDisplayImageOptions(int resId){
		return getDisplayImageOptions(true,resId);
	}
	public static DisplayImageOptions getDisplayImageOptions(boolean isFadein,int resId){
		DisplayImageOptions options;
		int fadeTime = 0;
//		if(isFadein)
//			fadeTime = 800;
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(resId)
				.showImageOnFail(resId)
				.cacheInMemory(true)
				.displayer(new FadeInBitmapDisplayer(fadeTime))
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		return options;
	
	}
}

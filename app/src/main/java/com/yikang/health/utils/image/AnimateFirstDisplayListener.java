package com.yikang.health.utils.image;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Bitmap;
import android.view.View;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.yikang.health.R;
import com.yikang.health.widget.RecyclingImageView;

/**
 * 
 * @author yangdefu
 * 
 */
public class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

	static final List<String> displayedImages = Collections
			.synchronizedList(new LinkedList<String>());

	@Override
	public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
		if (loadedImage != null) {
			RecyclingImageView imageView = (RecyclingImageView) view;
			// 是否第一次显示
			boolean firstDisplay = !displayedImages.contains(imageUri);
			if (firstDisplay) {
				// 图片淡入效果
				FadeInBitmapDisplayer.animate(imageView, 500);
				displayedImages.add(imageUri);
			}
		}else{
			view.setBackgroundResource(R.drawable.ic_launcher);
		}
	}
	
	@Override
	public void onLoadingFailed(String imageUri, View view,
			FailReason failReason) {
		// TODO Auto-generated method stub
		super.onLoadingFailed(imageUri, view, failReason);
//		view.setBackgroundResource(R.drawable.default_binner_bg);
	}
	
	@Override
	public void onLoadingCancelled(String imageUri, View view) {
		// TODO Auto-generated method stub
		super.onLoadingCancelled(imageUri, view);
//		view.setBackgroundResource(R.drawable.default_binner_bg);
	}
}

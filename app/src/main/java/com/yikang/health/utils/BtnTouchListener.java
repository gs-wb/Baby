package com.yikang.health.utils;

import android.graphics.ColorMatrixColorFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.yikang.health.widget.RecyclingImageView;

/**
 * 按钮点击效果
 * @author 
 *
 */

public class BtnTouchListener {
	private static  OnTouchListener TouchLight = new OnTouchListener() {

		private final float[] BT_SELECTED = new float[] { 1, 0, 0, 0, 50, 0, 1,
				0, 0, 50, 0, 0, 1, 0, 50, 0, 0, 0, 1, 0 };
		private final float[] BT_NOT_SELECTED = new float[] { 1, 0, 0, 0, 0, 0,
				1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if(v.getBackground() != null){
					v.getBackground().setColorFilter(
							new ColorMatrixColorFilter(BT_SELECTED));
					v.setBackgroundDrawable(v.getBackground());
				}
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				if(v.getBackground() != null){
					v.getBackground().setColorFilter(
							new ColorMatrixColorFilter(BT_NOT_SELECTED));
					v.setBackgroundDrawable(v.getBackground());
				}
			}
			return false;
		}
	};

	public static OnTouchListener TouchDark = new OnTouchListener() {

		private final float[] BT_SELECTED = new float[] { 1, 0, 0, 0, -10, 0, 1,
				0, 0, -10, 0, 0, 1, 0, -10, 0, 0, 0, 1, 0 };
		private final float[] BT_NOT_SELECTED = new float[] { 1, 0, 0, 0, 0, 0,
				1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if(v.getBackground() != null){
//					v.getBackground().setColorFilter(
//							new ColorMatrixColorFilter(BT_SELECTED));
//					v.setBackgroundDrawable(v.getBackground());
					v.getBackground().setAlpha(100);
				}else if(v instanceof RecyclingImageView){
					RecyclingImageView imageView = (RecyclingImageView)v;
					if(imageView.getDrawable()!=null){
						imageView.getDrawable().setAlpha(100);
					}
				}
			} else if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP) {
				if(v.getBackground() != null){
//					v.getBackground().setColorFilter(
//							new ColorMatrixColorFilter(BT_NOT_SELECTED));
//					v.setBackgroundDrawable(v.getBackground());
					v.getBackground().setAlpha(255);
				}else if(v instanceof RecyclingImageView){
					RecyclingImageView imageView = (RecyclingImageView)v;
					if(imageView.getDrawable()!=null){
						imageView.getDrawable().setAlpha(255);
					}
				}
			}
			return false;
		}
	};
}

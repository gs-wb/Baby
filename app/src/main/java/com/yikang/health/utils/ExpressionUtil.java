package com.yikang.health.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yikang.health.YIKApplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.text.ClipboardManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

public class ExpressionUtil {
	/**
	 * 得到一个SpanableString对象，通过传入的字符串,并进行正则判断
	 * 
	 * @param message
	 *            传入的字符串
	 * @return
	 */
	public static SpannableString getExpressionSmallString(Context context,
			String message) {
		String hackTxt;
		if (message != null && !"".equals(message)) {
			if (message.startsWith("[") && message.endsWith("]")) {
				hackTxt = message + " ";
			} else {
				hackTxt = message;
			}
			SpannableString value = SpannableString.valueOf(hackTxt);
			final Pattern EMOTION_URL = Pattern.compile("\\[(\\S+?)\\]");
			Matcher localMatcher = EMOTION_URL.matcher(value);
			while (localMatcher.find()) {
				String str2 = localMatcher.group(0);
				int k = localMatcher.start();
				int m = localMatcher.end();
				if (m - k < 8) {
					if (YIKApplication.instance.getmFaceMap()
							.containsKey(str2)) {
						int face = YIKApplication.instance.getmFaceMap()
								.get(str2);
						Bitmap bitmap = BitmapFactory.decodeResource(
								context.getResources(), face);
						if (bitmap != null) {
							int rawHeigh = bitmap.getHeight();
							int rawWidth = bitmap.getHeight();
							int newHeight = 30;
							int newWidth = 30;
							// 计算缩放因子
							float heightScale = ((float) newHeight) / rawHeigh;
							float widthScale = ((float) newWidth) / rawWidth;
							// 新建立矩阵
							Matrix matrix = new Matrix();
							matrix.postScale(heightScale, widthScale);
							// 设置图片的旋转角度
							// matrix.postRotate(-30);
							// 设置图片的倾斜
							// matrix.postSkew(0.1f, 0.1f);
							// 将图片大小压缩
							// 压缩后图片的宽和高以及kB大小均会变化
							Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0,
									0, rawWidth, rawHeigh, matrix, true);
							ImageSpan localImageSpan = new ImageSpan(context,
									newBitmap, ImageSpan.ALIGN_BOTTOM);
							value.setSpan(localImageSpan, k, m,
									Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
						}
					}
				}
			}
			return value;
		}else{
			return null;
		}

	}

	/**
	 * 得到一个SpanableString对象，通过传入的字符串,并进行正则判断
	 * 
	 * @param message
	 *            传入的字符串
	 * @return
	 */
	public static SpannableString getExpressionString(Context context,
			String message) {
		String hackTxt;
		if (message.startsWith("[") && message.endsWith("]")) {
			hackTxt = message + " ";
		} else {
			hackTxt = message;
		}
		SpannableString value = SpannableString.valueOf(hackTxt);
		final Pattern EMOTION_URL = Pattern.compile("\\[(\\S+?)\\]");
		Matcher localMatcher = EMOTION_URL.matcher(value);
		while (localMatcher.find()) {
			String str2 = localMatcher.group(0);
			int k = localMatcher.start();
			int m = localMatcher.end();
			if (m - k < 8) {
				if (YIKApplication.instance.getmFaceMap()
						.containsKey(str2)) {
					int face = YIKApplication.instance.getmFaceMap()
							.get(str2);
					Bitmap bitmap = BitmapFactory.decodeResource(
							context.getResources(), face);
					if (bitmap != null) {
						int rawHeigh = bitmap.getHeight();
						int rawWidth = bitmap.getHeight();
						int newHeight = 50;
						int newWidth = 50;
						// 计算缩放因子
						float heightScale = ((float) newHeight) / rawHeigh;
						float widthScale = ((float) newWidth) / rawWidth;
						// 新建立矩阵
						Matrix matrix = new Matrix();
						matrix.postScale(heightScale, widthScale);
						// 设置图片的旋转角度
						// matrix.postRotate(-30);
						// 设置图片的倾斜
						// matrix.postSkew(0.1f, 0.1f);
						// 将图片大小压缩
						// 压缩后图片的宽和高以及kB大小均会变化
						Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0,
								rawWidth, rawHeigh, matrix, true);
						ImageSpan localImageSpan = new ImageSpan(context,
								newBitmap, ImageSpan.ALIGN_BOTTOM);
						value.setSpan(localImageSpan, k, m,
								Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
					}
				}
			}
		}
		return value;
	}

	/**
	 * 实现粘贴功能 add by 王乾州
	 * 
	 * @param context
	 * @return
	 */
	public static String paste(Context context) {
		// 得到剪贴板管理器
		ClipboardManager cmb = (ClipboardManager) context
				.getSystemService(Context.CLIPBOARD_SERVICE);
		return cmb.getText().toString().trim();
	}
}
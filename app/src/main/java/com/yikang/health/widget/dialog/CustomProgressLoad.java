package com.yikang.health.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.widget.RecyclingImageView;
/**
 *通用加载dialog
 * @author zwb
 *
 */
public class CustomProgressLoad extends Dialog {
	private Context context = null;
	private static CustomProgressLoad customProgressDialog = null;

	public CustomProgressLoad(Context context) {
		super(context);
		this.context = context;
	}

	public CustomProgressLoad(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	public static CustomProgressLoad createDialog(Context context) {
		customProgressDialog = new CustomProgressLoad(context,
				R.style.CustomProgressDialog);
		customProgressDialog.setContentView(R.layout.custom_progress_load);
		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		customProgressDialog.onWindowFocusChanged(false);
		customProgressDialog.setCanceledOnTouchOutside(false);
		customProgressDialog.setCancelable(false);
		return customProgressDialog;
	}

	public void onWindowFocusChanged(boolean hasFocus) {

		if (customProgressDialog == null) {
			return;
		}
		ImageView imageView = (RecyclingImageView) customProgressDialog
				.findViewById(R.id.loading_ImageView);
		imageView.setImageResource(R.drawable.loading_01);
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotate_repeat);  
		animation.setInterpolator(new LinearInterpolator());  
		imageView.startAnimation(animation);
	}

	public CustomProgressLoad setTitile(String strTitle) {
		return customProgressDialog;
	}

	public CustomProgressLoad setMessage(String strMessage) {
		TextView tvMsg = (TextView) customProgressDialog
				.findViewById(R.id.id_tv_loadingmsg);

		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}

		return customProgressDialog;
	}
}

package com.yikang.health.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yikang.health.R;

public class PublicDialog extends Dialog implements
		View.OnClickListener {
	private Context context = null;
	private static PublicDialog customDialog = null;
	private DialogBtnClickListener listener;
	private Button mConfirm,mCancle;

	public interface DialogBtnClickListener {
		public void onClick(View view);
	}

	public PublicDialog(Context context) {
		super(context);
		this.context = context;
	}

	public PublicDialog(Context context, int theme,
			DialogBtnClickListener listener) {
		super(context, theme);
		this.context = context;
		this.listener = listener;
	}

	public static PublicDialog createDialog(Context context,
			DialogBtnClickListener listener) {
		customDialog = new PublicDialog(context,
				R.style.CustomProgressDialog, listener);
		customDialog.setContentView(R.layout.public_dialog);
		customDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		customDialog.setCanceledOnTouchOutside(false);
		customDialog.setCancelable(false);
		return customDialog;
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		if (customDialog == null) return;
		 mConfirm = (Button) customDialog
				.findViewById(R.id.public_dialog_confirm);
		 mCancle = (Button) customDialog
				.findViewById(R.id.public_dialog_cancel);
		mConfirm.setOnClickListener(this);
		mCancle.setOnClickListener(this);
	}

	/**
	 * 设置确定键文案
	 * @param str
	 */
	
	public void setConfirmContent(String str){
		mConfirm.setText(str);
	}
	
	/**
	 * 设置取消键文案
	 * @param str
	 */
	
	public void setCancleContent(String str){
		mCancle.setText(str);
	}

	public void setTitle(String strTitle) {
		TextView tvTitle = (TextView) customDialog
				.findViewById(R.id.dialog_title);

		if (tvTitle != null) {
			tvTitle.setText(strTitle);
		}
	}
	public void setContent(String strMessage) {
		TextView tvContent = (TextView) customDialog
				.findViewById(R.id.dialog_content);

		if (tvContent != null) {
			tvContent.setText(strMessage);
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		listener.onClick(v);
	}
	public void setCancelVisibility(boolean b) {
		// TODO Auto-generated method stub
		if(b){
			mCancle.setVisibility(View.VISIBLE);
		}else{
			mCancle.setVisibility(View.GONE);
		}
	}
}

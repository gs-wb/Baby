package com.yikang.health.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yikang.health.R;
/**
 * 通用Toast
 * @author hh
 *
 */
public class CustomToast extends Toast{
	
	private Context mContext;
	
	public CustomToast(Context context) {
		super(context);
		this.mContext = context;
	}

	public static CustomToast makeText(Context context, CharSequence text, int duration) {
		CustomToast result = new CustomToast(context);
		LayoutInflater inflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflate.inflate(R.layout.custom_toast, null);
		TextView tv = (TextView)v.findViewById(R.id.message);
		tv.setText(text);
		result.setView(v);
		result.setDuration(duration);
		result.setGravity(Gravity.CENTER, 0, 0);
		return result;
	}
		
	@Override
	public void setText(CharSequence s) {
		if (this.getView() == null) {
		    throw new RuntimeException("This Toast was not created with Toast.makeText()");
		}
		TextView tv = (TextView) this.getView().findViewById(R.id.message);
		if (tv == null) {
		    throw new RuntimeException("This Toast was not created with Toast.makeText()");
		}
	    tv.setText(s);
	}
	
	@Override
	public void setText(int resId) {
		setText(mContext.getText(resId));
	}
}

package com.yikang.health.widget;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils.TruncateAt;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.widget.EditText;

import com.yikang.health.utils.ExpressionUtil;

public class MyEditText extends EditText {
	private Context context;

	public MyEditText(Context context) {
		super(context);

		this.context = context;
	}

	public MyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.context = context;
	}

	public MyEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		this.context = context;
	}

	@Override
	protected boolean getDefaultEditable() {
		// TODO Auto-generated method stub
		return super.getDefaultEditable();
	}

	@Override
	protected MovementMethod getDefaultMovementMethod() {
		// TODO Auto-generated method stub
		return super.getDefaultMovementMethod();
	}

	@Override
	public Editable getText() {
		// TODO Auto-generated method stub
		return super.getText();
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		// TODO Auto-generated method stub
		super.setText(text, type);
	}

	@Override
	public void setSelection(int start, int stop) {
		// TODO Auto-generated method stub
		super.setSelection(start, stop);
	}

	@Override
	public void setSelection(int index) {
		// TODO Auto-generated method stub
		super.setSelection(index);
	}

	@Override
	public void selectAll() {
		// TODO Auto-generated method stub
		super.selectAll();
	}

	@Override
	public void extendSelection(int index) {
		// TODO Auto-generated method stub
		super.extendSelection(index);
	}

	@Override
	public void setEllipsize(TruncateAt ellipsis) {
		// TODO Auto-generated method stub
		super.setEllipsize(ellipsis);
	}

	@Override
	public boolean onTextContextMenuItem(int id) {
		switch (id) {
		case android.R.id.paste:
			/**
			 * 粘贴
			 */
			String msg = ExpressionUtil.paste(context);
			SpannableString spannableString = ExpressionUtil
					.getExpressionString(context, msg);
			getText().insert(getSelectionStart(), spannableString);
			return true;

		default:
			break;
		}
		return super.onTextContextMenuItem(id);
	}
}
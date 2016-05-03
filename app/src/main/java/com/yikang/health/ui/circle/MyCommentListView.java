package com.yikang.health.ui.circle;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyCommentListView extends ListView {

	public MyCommentListView(Context context) {
		super(context);
	}

	public MyCommentListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyCommentListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
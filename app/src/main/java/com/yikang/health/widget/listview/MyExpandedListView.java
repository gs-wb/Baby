/**
 * 
 */
package com.yikang.health.widget.listview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yikang.health.R;

/**
 * @author Jigx
 * 
 */
public class MyExpandedListView extends ExpandableListView implements
		View.OnClickListener, AbsListView.OnScrollListener {
	private FrameLayout parentLayout = null; // listview所在的父层对象
	private boolean isFixGroup = true; // 是否要固定显示组名在上边
	private View FixGroupView = null; // 始终固定显示的组view
	private ExpandableListAdapter mAdapter;
	private OnScrollListener mOnScrollListener = null;
	private int indicatorGroupId = -1; // 当前固定显示的组索引
	private int indicatorGroupHeight = 0;
	private LinearLayout indicatorGroup;
	private Context mContext;

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
	}

	public MyExpandedListView(Context context) {
		super(context);
		mContext = context;
		this.isFixGroup = true;
		super.setOnScrollListener(this);
	}

	public MyExpandedListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		this.isFixGroup = true;
		super.setOnScrollListener(this);
	}

	public MyExpandedListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		this.isFixGroup = true;
		super.setOnScrollListener(this);
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (isFixGroup) {// 是否显示固定组头
			boolean isFrameLayoutParent = getParent() instanceof FrameLayout;
			mAdapter = this.getExpandableListAdapter();
			if (isFrameLayoutParent) {// 当前listview是放到FrameLayout的容器中
				parentLayout = (FrameLayout) getParent();
				MyExpandedListView listView = (MyExpandedListView) view; // 得到当前listview
				if (null == indicatorGroup) {
					indicatorGroup = new LinearLayout(mContext);
					indicatorGroup.setLayoutParams(new MarginLayoutParams(
							ViewGroup.LayoutParams.FILL_PARENT,
							ViewGroup.LayoutParams.WRAP_CONTENT));
				}

				int npos = view.pointToPosition(0, 0);
				if (npos != AdapterView.INVALID_POSITION) {
					long pos = listView.getExpandableListPosition(npos);
					int childPos = ExpandableListView
							.getPackedPositionChild(pos);
					final int groupPos = ExpandableListView
							.getPackedPositionGroup(pos);
					if (childPos == AdapterView.INVALID_POSITION) {
						View groupView = listView.getChildAt(npos
								- listView.getFirstVisiblePosition());
						indicatorGroupHeight = groupView.getHeight();
					}
					if (indicatorGroupHeight == 0) {
						return;
					}
					if (groupPos != indicatorGroupId) {
						if (mAdapter != null) {
							FixGroupView = mAdapter.getGroupView(groupPos,
									false, null, indicatorGroup);
							FixGroupView.setLayoutParams(new LayoutParams(
									LayoutParams.FILL_PARENT,
									LayoutParams.WRAP_CONTENT));
							final ImageView iv = (ImageView) FixGroupView
									.findViewById(R.id.list_item_parent_icon);
//							if (isGroupExpanded(groupPos)) {
//								iv.setImageResource(R.drawable.list_close);
//							} else {
//								iv.setImageResource(R.drawable.list_open);
//							}
							FixGroupView
									.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
//											if (isGroupExpanded(groupPos)) {
//												collapseGroup(groupPos);
//												iv.setImageResource(R.drawable.list_open);
//											} else {
//												expandGroup(groupPos);
//												iv.setImageResource(R.drawable.list_close);
//											}
										}
									});
							indicatorGroupId = groupPos;
						}
					}
				}
				if (indicatorGroupId == -1) {
					return;
				}
				int showHeight = indicatorGroupHeight;
				int nEndPos = listView.pointToPosition(0, indicatorGroupHeight);
				if (nEndPos != AdapterView.INVALID_POSITION) {
					long pos = listView.getExpandableListPosition(nEndPos);
					int groupPos = ExpandableListView
							.getPackedPositionGroup(pos);
					if (groupPos != indicatorGroupId) {
						View viewNext = listView.getChildAt(nEndPos
								- listView.getFirstVisiblePosition());
						showHeight = viewNext.getTop();
					}
				}
				if (FixGroupView.getParent() == null) {
					indicatorGroup.removeAllViews();
					indicatorGroup.addView(FixGroupView);
				}
				ViewGroup.LayoutParams vlparams = indicatorGroup
						.getLayoutParams();
				if ((null != vlparams)
						&& (vlparams instanceof MarginLayoutParams)) {
					MarginLayoutParams layoutParams = (MarginLayoutParams) vlparams;
					layoutParams.topMargin = -(indicatorGroupHeight - showHeight);
					indicatorGroup.setLayoutParams(layoutParams);
					indicatorGroup.requestLayout();
				}

				if (indicatorGroup.getParent() == null) {
					parentLayout.addView(indicatorGroup);
				}
			}
		}
		if (this.mOnScrollListener != null)
			this.mOnScrollListener.onScroll(view, firstVisibleItem,
					visibleItemCount, totalItemCount);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (this.mOnScrollListener != null)
			this.mOnScrollListener.onScrollStateChanged(view, scrollState);
	}

	public void setOnScrollListener(
			OnScrollListener paramOnScrollListener) {
		this.mOnScrollListener = paramOnScrollListener;
	}

	public void setAdapter(ExpandableListAdapter paramExpandableListAdapter) {
		super.setAdapter(paramExpandableListAdapter);
	}

}

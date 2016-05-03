package com.yikang.health.ui.circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;

public class GridViewFaceAdapter extends BaseAdapter {
	private LayoutInflater inflater;

	private int currentPage = 0;

	private Map<String, Integer> mFaceMap;

	private List<Integer> faceList = new ArrayList<Integer>();

	public GridViewFaceAdapter(Context context, int currentPage) {
		this.inflater = LayoutInflater.from(context);
		this.currentPage = currentPage;
		mFaceMap = YIKApplication.instance.getmFaceMap();
		initData();
	}

	private void initData() {
		for (Map.Entry<String, Integer> entry : mFaceMap.entrySet()) {
			faceList.add(entry.getValue());
		}
	}

	@Override
	public int getCount() {
		return YIKApplication.instance.BASICFACE_NUM + 1;
	}

	@Override
	public Object getItem(int position) {
		return faceList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.circle_face_layout, null, false);
			viewHolder.faceIV = (ImageView) convertView
					.findViewById(R.id.face_iv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (position == YIKApplication.instance.BASICFACE_NUM) {
			viewHolder.faceIV.setImageResource(R.drawable.emotion_del_normal);
			viewHolder.faceIV.setBackgroundDrawable(null);
		} else {
			int count = YIKApplication.instance.BASICFACE_NUM
					* currentPage + position;
			if (count < 100) {
				viewHolder.faceIV.setImageResource(faceList.get(count));
			} else {
				viewHolder.faceIV.setImageDrawable(null);
			}
		}
		return convertView;
	}

	public static class ViewHolder {
		ImageView faceIV;
	}
}
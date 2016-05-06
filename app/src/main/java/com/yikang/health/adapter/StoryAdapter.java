package com.yikang.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.model.KnowledgeSubInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwb
 *
 */
public class StoryAdapter extends BaseAdapter{

	private Context ctxt;
	private List<String> list = new ArrayList<String>();
	public StoryAdapter(Context ctxt) {
		this.ctxt = ctxt;
	}

	public void setData(List<String> list) {
		this.list = list;
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(ctxt).inflate(
					R.layout.story_item_layout, null);
			viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_story_name);
			viewHolder.ivStory = (ImageView) convertView.findViewById(R.id.iv_story_icon);
			viewHolder.ivPlay = (ImageView) convertView.findViewById(R.id.iv_story_play);

			
			convertView.setTag(viewHolder);

		} else {
			viewHolder =  (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	class ViewHolder {
		public TextView tvName;
		public ImageView ivStory,ivPlay;
	}
	

}
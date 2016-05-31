package com.yikang.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.model.BabyLoreModel;
import com.yikang.health.model.WeatherModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwb
 *
 */
public class WeatherIndexAdapter extends BaseAdapter{

	private Context ctxt;
	private List<WeatherModel.TodayBean.IndexBean> list = new ArrayList<WeatherModel.TodayBean.IndexBean>();
	public WeatherIndexAdapter(Context ctxt) {
		this.ctxt = ctxt;
	}

	public void setData(List<WeatherModel.TodayBean.IndexBean> list) {
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
					R.layout.weather_index_item_layout, null);
			viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
			viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);

			convertView.setTag(viewHolder);

		} else {
			viewHolder =  (ViewHolder) convertView.getTag();
		}

		viewHolder.tvTitle.setText(list.get(position).getName()+"："+list.get(position).getIndex());
		viewHolder.tvContent.setText(list.get(position).getDetails());
		return convertView;
	}

	class ViewHolder {
		public TextView tvTitle,tvContent;
		public ImageView ivIcon;
	}
	

}
package com.yikang.health.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.model.BabyLoreModel;
/**
 * @author zwb
 *
 */
public class MainAdapter extends BaseAdapter{
	
	private Context ctxt;
	private List<BabyLoreModel> list = new ArrayList<BabyLoreModel>();
	public MainAdapter(Context ctxt) {
		this.ctxt = ctxt;
	}

	public void setData(List<BabyLoreModel> list) {
		// TODO Auto-generated method stub
		this.list = list;
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(ctxt).inflate(
					R.layout.baby_read_today_item_layout, null);
			viewHolder.tvHead = (TextView) convertView.findViewById(R.id.tv_listview_head);
			viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
			viewHolder.tvType = (TextView) convertView.findViewById(R.id.tv_type);
			viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
			
			convertView.setTag(viewHolder);

		} else {
			viewHolder =  (ViewHolder) convertView.getTag();
		}
		if(position == 0){
			viewHolder.tvHead.setVisibility(View.VISIBLE);
		}else{
			viewHolder.tvHead.setVisibility(View.GONE);
		}
//		viewHolder.tvTitle.setText(list.get(position).getTitle());
//		viewHolder.tvContent.setText(list.get(position).getDescription());
		return convertView;
	}

	class ViewHolder {
		public TextView tvHead,tvTitle,tvContent,tvType;
		public ImageView ivIcon;
	}
	

}
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
import com.yikang.health.model.KnowledgeInfo;
import com.yikang.health.model.KnowledgeInfo;
/**
 * @author zwb
 *
 */
public class KnowledgeLeftAdapter extends BaseAdapter{
	
	private Context ctxt;
	private List<KnowledgeInfo> list = new ArrayList<KnowledgeInfo>();
	public KnowledgeLeftAdapter(Context ctxt) {
		this.ctxt = ctxt;
	}

	public void setData(List<KnowledgeInfo> list) {
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
					R.layout.know_left_item_layout, null);
			viewHolder.tvKnowName = (TextView) convertView.findViewById(R.id.tv_know_name);
			viewHolder.ivKnowIcon = (ImageView) convertView.findViewById(R.id.iv_know_icon);
			viewHolder.leftLine =  convertView.findViewById(R.id.left_line);
			
			convertView.setTag(viewHolder);

		} else {
			viewHolder =  (ViewHolder) convertView.getTag();
		}
		viewHolder.tvKnowName.setText(list.get(position).getName());
		viewHolder.ivKnowIcon.setImageResource(list.get(position).getIcon());
		if(list.get(position).isSelected()){
			viewHolder.leftLine.setVisibility(View.VISIBLE);
			viewHolder.tvKnowName.setTextColor(ctxt.getResources().getColor(R.color.green_light));
		}else{
			viewHolder.leftLine.setVisibility(View.GONE);
			viewHolder.tvKnowName.setTextColor(ctxt.getResources().getColor(R.color.txt_dark));
		}
		return convertView;
	}

	class ViewHolder {
		public TextView tvKnowName;
		public ImageView ivKnowIcon;
		private View leftLine;
	}
	

}
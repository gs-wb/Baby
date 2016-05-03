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
import com.yikang.health.model.KnowledgeSubInfo;
import com.yikang.health.model.KnowledgeSubInfo;
/**
 * @author zwb
 *
 */
public class KnowledgeRightAdapter extends BaseAdapter{
	
	private Context ctxt;
	private List<KnowledgeSubInfo> list = new ArrayList<KnowledgeSubInfo>();
	public KnowledgeRightAdapter(Context ctxt) {
		this.ctxt = ctxt;
	}

	public void setData(List<KnowledgeSubInfo> list) {
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
					R.layout.know_right_item_layout, null);
			viewHolder.tvKnowName = (TextView) convertView.findViewById(R.id.iv_know_icon);
			
			convertView.setTag(viewHolder);

		} else {
			viewHolder =  (ViewHolder) convertView.getTag();
		}
		viewHolder.tvKnowName.setText(list.get(position).getName());
		return convertView;
	}

	class ViewHolder {
		public TextView tvKnowName;
	}
	

}
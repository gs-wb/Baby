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
import com.yikang.health.model.RecipesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwb
 *
 */
public class RecipesAdapter extends BaseAdapter{

	private Context ctxt;
	private List<RecipesModel> list = new ArrayList<RecipesModel>();
	public RecipesAdapter(Context ctxt) {
		this.ctxt = ctxt;
	}

	public void setData(List<RecipesModel> list) {
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
					R.layout.recipes_item_layout, null);
			viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
			viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
			
			convertView.setTag(viewHolder);

		} else {
			viewHolder =  (ViewHolder) convertView.getTag();
		}

		viewHolder.tvTitle.setText(list.get(position).getTitle());
		viewHolder.tvContent.setText(list.get(position).getContent());
		return convertView;
	}

	class ViewHolder {
		public TextView tvTitle,tvContent;
		public ImageView ivIcon;
	}
	

}
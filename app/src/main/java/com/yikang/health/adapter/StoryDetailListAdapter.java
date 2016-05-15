package com.yikang.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.yikang.health.R;
import com.yikang.health.model.Mp3Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter
 *
 * @author
 */
public class StoryDetailListAdapter extends BaseAdapter {
    private Context ctxt;
    private List<Mp3Info> list = new ArrayList<>();

    public StoryDetailListAdapter(Context ctxt) {
        this.ctxt = ctxt;
    }

    public void setList(List<Mp3Info> list) {
        this.list = list;
        notifyDataSetChanged();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(ctxt).inflate(R.layout.story_detail_list_item, parent, false);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.ivStoryPlay = (ImageView) convertView.findViewById(R.id.iv_story_play);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (list.get(position).isPlay()) {
            if(list.get(position).isNotPause()){
                Animation loadingAnimation = AnimationUtils.loadAnimation(ctxt, R.anim.rotate_all_repeat);
                LinearInterpolator lin = new LinearInterpolator();
                loadingAnimation.setInterpolator(lin);
                viewHolder.ivStoryPlay.startAnimation(loadingAnimation);
            }else{
                viewHolder.ivStoryPlay.clearAnimation();
            }
            viewHolder.ivStoryPlay.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivStoryPlay.clearAnimation();
            viewHolder.ivStoryPlay.setVisibility(View.GONE);
        }
        viewHolder.tv_name.setText(list.get(position).getId() + " --- " + list.get(position).getMp3_name());
        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
        ImageView ivStoryPlay;
    }
}

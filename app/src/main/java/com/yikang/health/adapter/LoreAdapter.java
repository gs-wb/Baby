package com.yikang.health.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yikang.health.R;
import com.yikang.health.constant.Constants;
import com.yikang.health.model.BabyLoreModel;

import java.lang.ref.WeakReference;

public class LoreAdapter extends LBaseAdapter<BabyLoreModel, LBaseAdapter.BaseViewHolder> {
    private WeakReference<Context> weakReference;

    public LoreAdapter(Context context) {
        super(context);
        weakReference = new WeakReference<>(context);
    }

    @Override
    protected BaseViewHolder createViewHolder(int position, ViewGroup parent) {
        return new BaseViewHolder(View.inflate(getContext(), R.layout.lore_item_layout, null));
    }

    @Override
    protected void bindViewHolder(BaseViewHolder holder, int position, BabyLoreModel data) {
        TextView txtTitle = holder.getView(R.id.tv_title);
        TextView txtContent = holder.getView(R.id.tv_content);
        ImageView icon = holder.getView(R.id.iv_icon);
        txtTitle.setText(data.getTitle());
        txtContent.setText(data.getDescription());
        if (weakReference != null && weakReference.get() != null)
            Picasso.with(weakReference.get())
                    .load("http://tnfs.tngou.net/image/" + data.getImg())
                    .placeholder(R.drawable.story_default_img)
                    .error(R.drawable.story_default_img)
                    .resize(100, 100)
                    .centerCrop()
                    .into(icon);
//        http://tnfs.tngou.net/image/lore/160511/173f5ab9dba7ae9c541e5c13e509370c.jpg
    }
}
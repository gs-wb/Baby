package com.yikang.health.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.model.BabyLoreModel;
import com.yikang.health.ui.menu.ArrayListFragment;
import com.yikang.health.ui.menu.PregnancyRecipesActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwb
 *
 */
public class RecipesFragmentAdapter extends FragmentStatePagerAdapter {

	public RecipesFragmentAdapter(FragmentManager fm) {
		super(fm);
	}
	/**
	 * 只需要实现下面两个方法即可。
	 */
	@Override
	public Fragment getItem(int position) {
		return ArrayListFragment.newInstance(position);
	}

	@Override
	public int getCount() {
		return PregnancyRecipesActivity.NUM_ITEMS;
	}

}
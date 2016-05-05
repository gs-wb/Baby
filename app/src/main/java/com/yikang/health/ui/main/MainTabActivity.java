package com.yikang.health.ui.main;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.cache.DataCache;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.utils.DisplayUtil;

public class MainTabActivity extends BaseActivity implements OnClickListener{

	private Fragment[] fragments;
	private BabyFragment babyFragment;
	private KnowledgeFragment knowledgeFragment;
	private StoryFragment storyFragment;
	private FountFragment findFragment;
	private MeFragment meFragment;
	private RadioButton[] mTabs;
	private int tabImgs[];
	private int index;
	// 当前fragment的index
	private int currentTabIndex = 0;
	public static MainTabActivity mInstance;
	
	public MainTabActivity() {
		// TODO Auto-generated constructor stub
		super();
		layoutResID = R.layout.activity_main_tab_layout;
 		mInstance = this;
	}
	@Override
	public void initIntent() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initControl() {
		initView();
		SlideMenu slideMenu = (SlideMenu) findViewById(R.id.slide_menu);
		babyFragment = new BabyFragment(slideMenu);
		knowledgeFragment = new KnowledgeFragment();
		storyFragment = new StoryFragment();
		findFragment = new FountFragment();
		meFragment = new MeFragment();
		fragments = new Fragment[] { babyFragment, knowledgeFragment,storyFragment,
				 findFragment, meFragment };
		// 添加显示第一个fragment
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, babyFragment)
				.add(R.id.fragment_container, knowledgeFragment).hide(knowledgeFragment)
				.add(R.id.fragment_container, storyFragment).hide(storyFragment)
				.add(R.id.fragment_container, findFragment).hide(findFragment)
				.add(R.id.fragment_container, meFragment).hide(meFragment)
				.show(babyFragment).commit();
		
	}
	/**
	 * 初始化组件
	 */
	private void initView() {
		tabImgs = new int[5];
		tabImgs[0] = R.drawable.main_tab_selector1;
		tabImgs[1] = R.drawable.main_tab_selector2;
		tabImgs[2] = R.drawable.main_tab_selector2;
		tabImgs[3] = R.drawable.main_tab_selector3;
		tabImgs[4] = R.drawable.main_tab_selector4;
		mTabs = new RadioButton[5];
		mTabs[0] = (RadioButton) findViewById(R.id.rb_baby);
		mTabs[1] = (RadioButton) findViewById(R.id.rb_knowledge);
		mTabs[2] = (RadioButton) findViewById(R.id.rb_story);
		mTabs[3] = (RadioButton) findViewById(R.id.rb_find);
		mTabs[4] = (RadioButton) findViewById(R.id.rb_me);
		// 把第一个tab设为选中状态
		for (int i = 0; i < mTabs.length; i++) {
			mTabs[i].setOnClickListener(this);
            int width=(int)DisplayUtil.px2dip(MainTabActivity.this, 28f);  
            int height=(int)DisplayUtil.px2dip(MainTabActivity.this, 28f);  
            Drawable d=getResources().getDrawable(tabImgs[i]);  
            d.setBounds(0, 0,width, height);  
            mTabs[i].setCompoundDrawables(null,d , null, null);  
		}
		
	}
	@Override
	protected void mSetTitleText(TextView mTitle) {
		// TODO Auto-generated method stub
		super.mSetTitleText(mTitle);
		mTitle.setText("运动健身");
	}
	@Override
	protected void leftButton(View arg0) {
		// TODO Auto-generated method stub
		super.leftButton(arg0);
	}
	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.rb_baby) {
			index = 0;
			swichFragment();
			setBarColor(getResources().getColor(R.color.main_bg));
		} else if (id == R.id.rb_knowledge) {
			index = 1;
			swichFragment();
			setBarColor(getResources().getColor(R.color.main_bg));
		} else if (id == R.id.rb_story) {
			index = 2;
			swichFragment();
			setBarColor(getResources().getColor(R.color.main_bg));
		} else if (id == R.id.rb_find) {
			index = 3;
			swichFragment();
			setBarColor(getResources().getColor(R.color.main_bg));
		} else if (id == R.id.rb_me) {
			index = 4;
			swichFragment();
			setBarColor(getResources().getColor(R.color.main_red));
		}
	}
	private void swichFragment() {
		// TODO Auto-generated method stub
		if (currentTabIndex != index) {
			FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
			trx.hide(fragments[currentTabIndex]);
			if (!fragments[index].isAdded()) {
				trx.add(R.id.fragment_container, fragments[index]);
			}
			trx.show(fragments[index]).commit();
		}
		currentTabIndex = index;
	}
	@Override
	public void initVariable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void backBtnFunction() {
		// TODO Auto-generated method stub
		DataCache.getDataCache().clean();
	}
}

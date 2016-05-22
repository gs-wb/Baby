package com.yikang.health.ui.menu;

import android.support.v4.view.ViewPager;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.yikang.health.R;
import com.yikang.health.adapter.RecipesFragmentAdapter;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.utils.Utils;

import java.util.ArrayList;

/**
 * Created by zwb on 2016/5/21.
 */
public class PregnancyRecipesActivity extends BaseActivity {
    public static final int NUM_ITEMS = 12;
    private RecipesFragmentAdapter recipesAdapter;
    private ViewPager mPager;
    private HorizontalScrollView scroll_veiw;
    private ArrayList<RadioButton> viewList = new ArrayList<>();

    public PregnancyRecipesActivity() {
        super();
        layoutResID = R.layout.activity_pregnancy_recipes_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText("孕期食谱");
    }

    @Override
    public void initIntent() {

    }

    @Override
    public void initControl() {
        super.initControl();
        initView();
        /**
         * 同样，由于调用的是support.v4的包，这里是getSupportFragmentManager()，而不是getFragmentManager();
         */
        recipesAdapter = new RecipesFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(recipesAdapter);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int w = Utils.dip2px(PregnancyRecipesActivity.this,80f);
                if(position-1>=0){

                    for (int i=0;i<12;i++) {
                        viewList.get(i).setChecked(false);
                        viewList.get(i).setTextColor(getResources().getColor(R.color.main_red));
                        if(position-1 == i){
                            viewList.get(i).setChecked(true);
                            viewList.get(i).setTextColor(getResources().getColor(R.color.color_ffffff));
                        }
                    }
                }
                scroll_veiw.smoothScrollTo(position*w,0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    RadioButton tempRadio;
    private void initView() {
        scroll_veiw = (HorizontalScrollView) findViewById(R.id.scroll_veiw);
        viewList.add((RadioButton) findViewById(R.id.pregnancy_1));
        viewList.add((RadioButton) findViewById(R.id.pregnancy_2));
        viewList.add((RadioButton) findViewById(R.id.pregnancy_3));
        viewList.add((RadioButton) findViewById(R.id.pregnancy_4));
        viewList.add((RadioButton) findViewById(R.id.pregnancy_5));
        viewList.add((RadioButton) findViewById(R.id.pregnancy_6));
        viewList.add((RadioButton) findViewById(R.id.pregnancy_7));
        viewList.add((RadioButton) findViewById(R.id.pregnancy_8));
        viewList.add((RadioButton) findViewById(R.id.pregnancy_9));
        viewList.add((RadioButton) findViewById(R.id.pregnancy_10));
        viewList.add((RadioButton) findViewById(R.id.pregnancy_11));
        viewList.add((RadioButton) findViewById(R.id.pregnancy_12));


    }

    @Override
    public void initVariable() {

    }

    @Override
    public void initObserver() {

    }

    @Override
    public void saveData() {

    }

    @Override
    public void backBtnFunction() {

    }
}

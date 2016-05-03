package com.yikang.health.ui.circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.adapter.CircleAdapter;
import com.yikang.health.model.WorkCircleInfo;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.ui.circle.JazzyViewPager.TransitionEffect;
import com.yikang.health.utils.SharePreferenceUtil;
import com.yikang.health.widget.MyEditText;

public class CircleActivity extends BaseActivity implements OnClickListener{
	public static CircleActivity instance;
	private ListView lvCircle;
	private CircleAdapter circleAdapter;
	private ArrayList<WorkCircleInfo> circleInfos = new ArrayList<WorkCircleInfo>();
	/* 回复的布局 */
	private View workcircleReplyLayout;
	/* 表情按钮，发送按钮 */
	private ImageButton msg_express;
	private Button msg_submit;
	/*  输入框  */
    private MyEditText msg_content;
    /* 表情翻页效果  */
    private TransitionEffect mEffects[] = {TransitionEffect.Standard, TransitionEffect.Tablet, TransitionEffect.CubeIn,
        TransitionEffect.CubeOut, TransitionEffect.FlipVertical, TransitionEffect.FlipHorizontal,
        TransitionEffect.Stack, TransitionEffect.ZoomIn, TransitionEffect.ZoomOut, TransitionEffect.RotateUp,
        TransitionEffect.RotateDown, TransitionEffect.Accordion,};
    
    /* 表情当前页 */
    private int faceCurrentPage = 0;
    
    /*  表情key  */
    private List<String> keys;
    
    /*  表情区域  */
    private RelativeLayout facelayout;
    /*  表情左右滑动 */
    private JazzyViewPager tabpager;
    /**
     * 临时保存到文件中数据
     */
    private SharePreferenceUtil mSpUtil;
	public CircleActivity() {
		super();
		layoutResID = R.layout.activity_circle_layout;
		instance = this;
	}
	@Override
	public void initIntent() {
		
	}

	@Override
	public void initVariable() {
		
	}

	@Override
	public void initControl() {
		super.initControl();
		lvCircle = (ListView) findViewById(R.id.lv_circle);
		workcircleReplyLayout = (View) findViewById(R.id.workcircleReplyLayout);
		msg_express = (ImageButton) findViewById(R.id.msg_express);
		msg_express.setTag(1);
		msg_submit = (Button)findViewById(R.id.msg_submit);
        msg_content = (MyEditText)findViewById(R.id.msg_content);
		circleAdapter = new CircleAdapter(this);
		lvCircle.setAdapter(circleAdapter);
		msg_express.setOnClickListener(this);
		/**
         * 初始化表情视图
         */
        initFaceView();
        /**
         * 预加载表情,默认加载基本表情
         */
        loadFace();
        
        laodData();
	}
	
	private void laodData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 6; i++) {
			circleInfos.add(new WorkCircleInfo());
		}
		circleAdapter.setData(circleInfos);
	}
	private void initFaceView(){
		mSpUtil = YIKApplication.instance.getSpUtil();
        facelayout = (RelativeLayout)findViewById(R.id.facelayout);
        tabpager = (JazzyViewPager)findViewById(R.id.tabpager);
        Set<String> keySet = YIKApplication.instance.getmFaceMap().keySet();
        keys = new ArrayList<String>();
        keys.addAll(keySet);
    }
	
	private void loadFace() {
		List<View> lv = new ArrayList<View>();
		for (int i = 0; i < 5; ++i)
			lv.add(getGridView(i));
		ViewPagerAdapter adapter = new ViewPagerAdapter(lv, tabpager);
		tabpager.setAdapter(adapter);
		tabpager.setCurrentItem(faceCurrentPage);
		tabpager.setTransitionEffect(mEffects[mSpUtil.getFaceEffect()]);
		CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
		indicator.setViewPager(tabpager);
		adapter.notifyDataSetChanged();
		indicator.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				faceCurrentPage = arg0;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// do nothing
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// do nothing
			}
		});
	}
	
	private GridView getGridView(int i) {
		GridView gv = new GridView(this);
		gv.setNumColumns(7);
		gv.setSelector(new ColorDrawable(Color.TRANSPARENT));// 屏蔽GridView默认点击效果
		gv.setBackgroundColor(Color.TRANSPARENT);
		gv.setCacheColorHint(Color.TRANSPARENT);
		gv.setHorizontalSpacing(1);
		gv.setVerticalSpacing(10);
		gv.setScrollbarFadingEnabled(false);
		gv.setFastScrollEnabled(false);
		gv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		gv.setGravity(Gravity.CENTER);
		gv.setAdapter(new GridViewFaceAdapter(this, i));
		gv.setOnTouchListener(forbidenScroll());
		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == YIKApplication.instance.BASICFACE_NUM) {
					try {
						// 删除键的位置
						int selection = msg_content.getSelectionStart();
						String text = msg_content.getText().toString();
						if (selection > 0) {
							String text2 = text.substring(selection - 1);
							if ("]".equals(text2)) {
								int start = text.lastIndexOf("[");
								int end = selection;
								msg_content.getText().delete(start, end);
								return;
							}
							msg_content.getText().delete(selection - 1,
									selection);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					int count = faceCurrentPage
							* YIKApplication.instance.BASICFACE_NUM
							+ arg2;
					// 注释的部分，在EditText中显示字符串
					// String ori = msgEt.getText().toString();
					// int index = msgEt.getSelectionStart();
					// StringBuilder stringBuilder = new StringBuilder(ori);
					// stringBuilder.insert(index, keys.get(count));
					// msgEt.setText(stringBuilder.toString());
					// msgEt.setSelection(index + keys.get(count).length());

					// 下面这部分，在EditText中显示表情
					Bitmap bitmap = BitmapFactory.decodeResource(
							getResources(), (Integer) YIKApplication.instance.getmFaceMap().values()
									.toArray()[count]);
					if (bitmap != null) {
						int rawHeigh = bitmap.getHeight();
						int rawWidth = bitmap.getHeight();
						int newHeight = 40;
						int newWidth = 40;
						// 计算缩放因子
						float heightScale = ((float) newHeight) / rawHeigh;
						float widthScale = ((float) newWidth) / rawWidth;
						// 新建立矩阵
						Matrix matrix = new Matrix();
						matrix.postScale(heightScale, widthScale);
						// 设置图片的旋转角度
						// matrix.postRotate(-30);
						// 设置图片的倾斜
						// matrix.postSkew(0.1f, 0.1f);
						// 将图片大小压缩
						// 压缩后图片的宽和高以及kB大小均会变化
						Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0,
								rawWidth, rawHeigh, matrix, true);
						ImageSpan imageSpan = new ImageSpan( CircleActivity.this, newBitmap);
						String emojiStr = keys.get(count);
						SpannableString spannableString = new SpannableString(
								emojiStr);
						spannableString.setSpan(imageSpan,
								emojiStr.indexOf('['),
								emojiStr.indexOf(']') + 1,
								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						msg_content.getText().insert(
								msg_content.getSelectionStart(),
								spannableString);
					} else {
						String ori = msg_content.getText().toString();
						int index = msg_content.getSelectionStart();
						StringBuilder stringBuilder = new StringBuilder(ori);
						stringBuilder.insert(index, keys.get(count));
						msg_content.setText(stringBuilder.toString());
						msg_content.setSelection(index
								+ keys.get(count).length());
					}
				}
			}
		});
		return gv;
	}

	/**
	 * <防止pageview乱滚动>
	 */
	private OnTouchListener forbidenScroll() {
		return new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					return true;
				}
				return false;
			}
		};
	}
	public void setReplyLayoutVisibility(int v) {
		workcircleReplyLayout.setVisibility(v);
	}

	public View getReplyLayout() {
		// TODO Auto-generated method stub
		return workcircleReplyLayout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.msg_express:
			/**
			 * 表情按钮
			 */
			if ((Integer) msg_express.getTag() == 1) {
				msg_express.setTag(2);
				// 更换为键盘按钮,显示表情区域
				msg_express
						.setBackgroundResource(R.drawable.workcircle_keyboard_pressed);
				showFace();
			} else {
				msg_express.setTag(1);

				// 更换为表情按钮,隐藏表情区域
				msg_express
						.setBackgroundResource(R.drawable.workcircle_face_xml);

				hideFace();
			}
			break;
		}
	}

	private void showFace() {
		facelayout.setVisibility(View.VISIBLE);
	}

	private void hideFace() {
		facelayout.setVisibility(View.GONE);
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
		
	}

}

package com.yikang.health.ui.main;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.adapter.KnowledgeLeftAdapter;
import com.yikang.health.adapter.KnowledgeRightAdapter;
import com.yikang.health.model.KnowledgeInfo;
import com.yikang.health.model.KnowledgeSubInfo;
import com.yikang.health.ui.BaseFragment;

public class KnowledgeFragment extends BaseFragment implements OnItemClickListener{

	private ListView lvKnowledge;;
	private GridView gvKnowledge;
	private KnowledgeLeftAdapter leftAdapter;
	private KnowledgeRightAdapter rightAdapter;

	private List<KnowledgeInfo> leftList = new ArrayList<KnowledgeInfo>();
	private List<KnowledgeSubInfo> rightList = new ArrayList<KnowledgeSubInfo>();
	private List<KnowledgeSubInfo> currRightList = new ArrayList<KnowledgeSubInfo>();
	
	public KnowledgeFragment() {
		super();
		 layoutResID = R.layout.fragment_main_knowledge_layout;
	}
	@Override
	protected void mSetTitleText(TextView mTitle) {
		// TODO Auto-generated method stub
		super.mSetTitleText(mTitle);
		mTitle.setText("知识库");
	}
	@Override
	public void initControl(View v) {
		lvKnowledge = (ListView)v.findViewById(R.id.lv_knowledge);
		leftAdapter = new KnowledgeLeftAdapter(getActivity());
		lvKnowledge.setAdapter(leftAdapter);
		lvKnowledge.setOnItemClickListener(this);
		gvKnowledge = (GridView)v.findViewById(R.id.gv_knowledge);
		rightAdapter = new KnowledgeRightAdapter(getActivity());
		gvKnowledge.setAdapter(rightAdapter);
		loadData();
	}

	private void loadData() {
		leftList.add(new KnowledgeInfo("1","备孕", R.drawable.ic_launcher,true));
		leftList.add(new KnowledgeInfo("2","孕期", R.drawable.ic_launcher,false));
		leftList.add(new KnowledgeInfo("3","新生儿", R.drawable.ic_launcher,false));
		leftList.add(new KnowledgeInfo("4","婴幼儿", R.drawable.ic_launcher,false));
		leftAdapter.setData(leftList);
		
		rightList.add(new KnowledgeSubInfo("11","1","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("12","1","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("13","1","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","1","孕期护理"));
		rightList.add(new KnowledgeSubInfo("11","1","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","1","孕期护理"));
		rightList.add(new KnowledgeSubInfo("14","1","孕期护理"));
		rightList.add(new KnowledgeSubInfo("14","1","孕期护理"));
		rightList.add(new KnowledgeSubInfo("14","1","孕期护理"));
		rightList.add(new KnowledgeSubInfo("11","1","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","1","孕期护理"));
		rightList.add(new KnowledgeSubInfo("11","1","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","1","孕期护理"));
		rightList.add(new KnowledgeSubInfo("14","1","孕期护理"));
		rightList.add(new KnowledgeSubInfo("11","1","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","1","孕期护理"));
		rightList.add(new KnowledgeSubInfo("11","1","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","1","孕期护理"));
		rightList.add(new KnowledgeSubInfo("14","1","孕期护理"));
		rightList.add(new KnowledgeSubInfo("11","1","婴儿发育"));
		
		rightList.add(new KnowledgeSubInfo("21","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("22","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("23","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("24","2","饮食技巧"));
		rightList.add(new KnowledgeSubInfo("21","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("22","2","分娩生产"));
		rightList.add(new KnowledgeSubInfo("23","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("22","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("23","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("24","2","饮食技巧"));
		rightList.add(new KnowledgeSubInfo("21","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("22","2","分娩生产"));
		rightList.add(new KnowledgeSubInfo("21","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("24","2","饮食技巧"));
		rightList.add(new KnowledgeSubInfo("21","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("22","2","分娩生产"));
		rightList.add(new KnowledgeSubInfo("22","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("23","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("24","2","饮食技巧"));
		rightList.add(new KnowledgeSubInfo("21","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("22","2","分娩生产"));
		rightList.add(new KnowledgeSubInfo("23","2","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","2","婴儿发育"));
		
		rightList.add(new KnowledgeSubInfo("11","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("12","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("13","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("11","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("11","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("12","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("13","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("11","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("11","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("11","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("12","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("13","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("11","3","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","3","婴儿发育"));
		
		rightList.add(new KnowledgeSubInfo("11","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("12","4","日常生活"));
		rightList.add(new KnowledgeSubInfo("13","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("11","4","胎教技巧"));
		rightList.add(new KnowledgeSubInfo("14","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("11","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("12","4","日常生活"));
		rightList.add(new KnowledgeSubInfo("13","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("11","4","胎教技巧"));
		rightList.add(new KnowledgeSubInfo("11","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("12","4","日常生活"));
		rightList.add(new KnowledgeSubInfo("13","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("11","4","胎教技巧"));
		rightList.add(new KnowledgeSubInfo("13","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("12","4","日常生活"));
		rightList.add(new KnowledgeSubInfo("13","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("11","4","胎教技巧"));
		rightList.add(new KnowledgeSubInfo("13","4","婴儿发育"));
		rightList.add(new KnowledgeSubInfo("14","4","婴儿发育"));
		for (int i = 0; i < rightList.size(); i++) {
			if(leftList.get(0).getId().equals(rightList.get(i).getParentId())){
				currRightList.add(rightList.get(i));
			}
		}
		rightAdapter.setData(currRightList);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		currRightList.clear();
		for (int i = 0; i < rightList.size(); i++) {
			if(leftList.get(position).getId().equals(rightList.get(i).getParentId())){
				currRightList.add(rightList.get(i));
			}
		}
		rightAdapter.setData(currRightList);
		for (int i = 0; i < leftList.size(); i++) {
			if(i == position){
				leftList.get(position).setSelected(true);
			}else{
				leftList.get(i).setSelected(false);
			}
		}
		leftAdapter.notifyDataSetChanged();
	}
	@Override
	public void initObserver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
	}

	@Override
	public void onDestroy() {
	}
	

}

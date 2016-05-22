package com.yikang.health.ui.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.adapter.RecipesAdapter;
import com.yikang.health.model.RecipesModel;

import java.util.ArrayList;

public class ArrayListFragment extends Fragment {
    private int mNum;

    /**
     * 创建一个计算Fragment页面的实例，将怒num作为一个参数。
     * （Create a new instance of
     * CountingFragment, providing "num" as an argument.）
     */
    public static ArrayListFragment newInstance(int num) {

        ArrayListFragment f = new ArrayListFragment();
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    /**
     * 当调用该方法时，检索此实例的数量的参数。
     * （When creating, retrieve this instance's number from
     * its arguments.）
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    /**
     * Fragment的UI只显示它所在的页码。
     * （The Fragment's UI is just a simple text view
     * showing its instance number.）
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipes_body_layout, container, false);
        tv_recipes_desc = (TextView) view.findViewById(R.id.tv_recipes_desc);
        recipes_list = (ListView) view.findViewById(R.id.recipes_list);
        View headerView  = inflater.inflate(R.layout.recipes_header_layout,null,false);
        recipes_list.addHeaderView(headerView);
        return view;
    }

    TextView tv_recipes_desc;
    ListView recipes_list;
    RecipesAdapter recipesAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() == null) return;
        recipesAdapter = new RecipesAdapter(getActivity());
        recipes_list.setAdapter(recipesAdapter);
        loadData();
    }

    /**
     * 在每一个Fragment列表中展示的数据。
     */
    public void loadData() {
        ArrayList<RecipesModel> list = new ArrayList<>();
        int count = 5;
        switch (mNum){
            case 1:
                count = 5;
                break;
            case 2:
                count = 3;
                break;
            case 3:
                count = 5;
                break;
            case 4:
                count = 3;
                break;
            case 5:
                count = 5;
                break;
            case 6:
                count = 3;
                break;
            case 7:
                count = 5;
                break;
            case 8:
                count = 3;
                break;
            case 9:
                count = 5;
                break;
            case 10:
                count = 3;
                break;
            case 11:
                count = 5;
                break;
            case 12:
                count = 3;
                break;
        }

        for (int i = 0;i<count;i++){
            list.add(new RecipesModel("清蒸鲈鱼","鲈鱼营养价值高，容易被吸收，比起普通的鱼类更优",""));
        }
        recipesAdapter.setData(list);
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        Toast.makeText(getActivity(), "您点击了" + position, Toast.LENGTH_LONG).show();
//    }

}

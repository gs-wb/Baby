//package com.yikang.health.ui.me;
//
//import android.content.Intent;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.AdapterView;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.yikang.health.R;
//import com.yikang.health.ui.BaseActivity;
//import com.yikang.health.utils.image.LocalImageHelper;
//
//import java.util.List;
//
///**
// * Created by zwb on 2016/5/2.
// */
//public class PhotoListActivity extends BaseActivity implements View.OnClickListener{
//    GridView gridView;
//
//    public PhotoListActivity(){
//        super();
//        layoutResID = R.layout.activity_photo_manage_layout;
//    }
//
//    @Override
//    protected void mSetTitleText(TextView mTitle) {
//        super.mSetTitleText(mTitle);
//        mTitle.setText("宝宝照片");
//    }
//
//    @Override
//    public void initIntent() {
//
//    }
//
//    @Override
//    public void initControl() {
//        super.initControl();
//        listView = (ListView) findViewById(R.id.local_album_list);
//        progress = (ImageView) findViewById(R.id.progress_bar);
//        helper = LocalImageHelper.getInstance();
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_repeat);
//        progress.startAnimation(animation);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //开启线程初始化本地图片列表，该方法是synchronized的，因此当AppContent在初始化时，此处阻塞
//                LocalImageHelper.getInstance().initImage();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //初始化完毕后，显示文件夹列表
//                        if (!PhotoListActivity.this.isFinishing()) {
//                            initAdapter();
//                            progress.clearAnimation();
//                            ((View) progress.getParent()).setVisibility(View.GONE);
//                            listView.setVisibility(View.VISIBLE);
//                        }
//                    }
//                });
//            }
//        }).start();
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(PhotoListActivity.this, LocalAlbumDetail.class);
//                intent.putExtra(LOCAL_FOLDER_NAME, folderNames.get(i));
//                intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
//                startActivity(intent);
//            }
//        });
//    }
//    public void initAdapter() {
//        listView.setAdapter(new FolderAdapter(this, helper.getFolderMap()));
//    }
//    @Override
//    public void initVariable() {
//
//    }
//
//    @Override
//    public void initObserver() {
//    }
//
//    @Override
//    public void onClick(View v) {
//    }
//
//    @Override
//    public void saveData() {
//
//    }
//
//    @Override
//    public void backBtnFunction() {
//
//    }
//}

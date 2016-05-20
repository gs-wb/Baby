package com.yikang.health.ui.me;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.utils.image.LocalImageHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zwb on 2016/5/2.
 */
public class PhotoManageActivity extends BaseActivity implements View.OnClickListener{
    ListView listView;
    ImageView progress;
    LocalImageHelper helper;
    List<String> folderNames;
    public static final String LOCAL_FOLDER_NAME="local_folder_name";//跳转到相册页的文件夹名称

    public PhotoManageActivity(){
        super();
        layoutResID = R.layout.activity_photo_manage_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText("宝宝照片");
    }

    @Override
    public void initIntent() {

    }

    @Override
    public void initControl() {
        super.initControl();
        listView = (ListView) findViewById(R.id.local_album_list);
        progress = (ImageView) findViewById(R.id.progress_bar);
        helper = LocalImageHelper.getInstance();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_repeat);
        progress.startAnimation(animation);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //开启线程初始化本地图片列表，该方法是synchronized的，因此当AppContent在初始化时，此处阻塞
                LocalImageHelper.getInstance().initImage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //初始化完毕后，显示文件夹列表
                        if (!PhotoManageActivity.this.isFinishing()) {
                            initAdapter();
                            progress.clearAnimation();
                            ((View) progress.getParent()).setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }).start();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(PhotoManageActivity.this, PhotoListActivity.class);
//                intent.putExtra(LOCAL_FOLDER_NAME, folderNames.get(i));
//                intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
//                startActivity(intent);
            }
        });
    }
    public void initAdapter() {
        listView.setAdapter(new FolderAdapter(this, helper.getFolderMap()));
    }


    @Override
    public void initVariable() {

    }

    @Override
    public void initObserver() {
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void saveData() {

    }

    @Override
    public void backBtnFunction() {

    }

    public class FolderAdapter extends BaseAdapter {
        Map<String, List<LocalImageHelper.LocalFile>> folders;
        Context context;
        DisplayImageOptions options;

        FolderAdapter(Context context, Map<String, List<LocalImageHelper.LocalFile>> folders) {
            this.folders = folders;
            this.context = context;
            folderNames = new ArrayList<>();

            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(false)
                    .showImageForEmptyUri(R.drawable.dangkr_no_picture_small)
                    .showImageOnFail(R.drawable.dangkr_no_picture_small)
                    .showImageOnLoading(R.drawable.dangkr_no_picture_small)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .setImageSize(new ImageSize(width/4, 0))
                    .displayer(new SimpleBitmapDisplayer()).build();

            Iterator iter = folders.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                folderNames.add(key);
            }
            //根据文件夹内的图片数量降序显示
            Collections.sort(folderNames, new Comparator<String>() {
                public int compare(String arg0, String arg1) {
                    Integer num1 = helper.getFolder(arg0).size();
                    Integer num2 = helper.getFolder(arg1).size();
                    return num2.compareTo(num1);
                }
            });
        }

        @Override
        public int getCount() {
            return folders.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (convertView == null || convertView.getTag() == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_albumfoler, null);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.textview);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String name = folderNames.get(i);
            List<LocalImageHelper.LocalFile> files = folders.get(name);
            viewHolder.textView.setText(name + "(" + files.size() + ")");
            if (files.size() > 0) {
                ImageLoader.getInstance().displayImage(files.get(0).getThumbnailUri(), new ImageViewAware(viewHolder.imageView), options,
                        null, null, files.get(0).getOrientation());
            }
            return convertView;
        }

        private class ViewHolder {
            ImageView imageView;
            TextView textView;
        }
    }
}

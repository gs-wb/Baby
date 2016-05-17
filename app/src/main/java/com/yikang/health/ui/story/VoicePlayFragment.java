package com.yikang.health.ui.story;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.constant.Constants;
import com.yikang.health.model.Mp3Info;
import com.yikang.health.service.PlayerService;
import com.yikang.health.ui.BaseFragment;
import com.yikang.health.utils.MediaUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by zwb on 2016/5/15.
 */
@SuppressLint("ValidFragment")
public class VoicePlayFragment extends BaseFragment implements View.OnClickListener {
    private Button previousBtn; // 上一首
    private Button playBtn; // 播放（播放、暂停）
    private Button queueBtn; // 播放（播放、暂停）
    private ImageView play_music_bg; // 播放（播放、暂停）
    private Button nextBtn; // 下一首
    private SeekBar music_progressBar; // 歌曲进度
    private TextView currentProgress; // 当前进度消耗的时间
    private TextView finalProgress; // 歌曲时间
    private Animation loadingAnimation;

    private int listPosition; // 播放歌曲在mp3Infos的位置
    private int currentTime; // 当前歌曲播放时间
    private int flag = 1; // 播放标识
    private boolean isShowBoby = false;
    private boolean isPlaying; // 正在播放
    private boolean isPause; // 暂停

    private ArrayList<Mp3Info> mp3Infos = new ArrayList<>();


    private PlayerReceiver playerReceiver;


    public VoicePlayFragment(ArrayList<Mp3Info> mp3Infos, int listPosition) {
        super();
        layoutResID = R.layout.play_voice_layout;
        this.mp3Infos = mp3Infos;
        this.listPosition = listPosition;
    }

    @Override
    public void initControl(View view) {
        findViewById(view);
        registerReceiver();
        initView();        //初始化视图
        getActivity().bindService(new Intent(getActivity(), PlayerService.class),
                mConnection, Context.BIND_AUTO_CREATE | Context.BIND_DEBUG_UNBIND);
//        setBarColor(getResources().getColor(R.color.color_ffffff));
    }

    private void unbindService() {
        if (mConnection != null) getActivity().unbindService(mConnection);
    }

    private PlayerService mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = ((PlayerService.PlayerBinder) service).getService();
            mService.setDate(mp3Infos,listPosition);
            mService.updatePlayMsg(Constants.PlayerMsg.PLAY_MSG, 0);
        }

        public void onServiceDisconnected(ComponentName className) {
            mService = null;
        }
    };

    /**
     * 从界面上根据id获取按钮
     */
    private void findViewById(View view) {
        previousBtn = (Button) view.findViewById(R.id.previous_music);
        playBtn = (Button) view.findViewById(R.id.play_music);
        play_music_bg = (ImageView) view.findViewById(R.id.play_music_bg);
        nextBtn = (Button) view.findViewById(R.id.next_music);
        queueBtn = (Button) view.findViewById(R.id.play_queue);
        music_progressBar = (SeekBar) view.findViewById(R.id.audioTrack);
        currentProgress = (TextView) view.findViewById(R.id.current_progress);
        finalProgress = (TextView) view.findViewById(R.id.final_progress);
        loadingAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_repeat);
    }

    private void registerReceiver() {
        //定义和注册广播接收器
        playerReceiver = new PlayerReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.PlayerMsg.UPDATE_ACTION);
        filter.addAction(Constants.PlayerMsg.MUSIC_CURRENT);
        filter.addAction(Constants.PlayerMsg.MUSIC_DURATION);
        getActivity().registerReceiver(playerReceiver, filter);
    }

    @Override
    public void initObserver() {
        ViewOnclickListener ViewOnClickListener = new ViewOnclickListener();
        previousBtn.setOnClickListener(ViewOnClickListener);
        playBtn.setOnClickListener(ViewOnClickListener);
        nextBtn.setOnClickListener(ViewOnClickListener);
        queueBtn.setOnClickListener(ViewOnClickListener);
        music_progressBar.setOnSeekBarChangeListener(new SeekBarChangeListener());
    }

    /**
     * 初始化界面
     */
    public void initView() {
        isPlaying = true;
        isPause = false;
        if (flag == Constants.PlayerMsg.PLAY_MSG) { // 如果是点击列表播放歌曲的话
            playBtn.setBackgroundResource(R.drawable.voice_pause_selector);
//            play();
        } else if (flag == Constants.PlayerMsg.CONTINUE_MSG) {
            playBtn.setBackgroundResource(R.drawable.voice_play_selector);
//            mService.updatePlayMsg(Constants.PlayerMsg.CONTINUE_MSG, 0);
//            Intent intent = new Intent(getActivity(), PlayerService.class);
//            playBtn.setBackgroundResource(R.drawable.voice_play_selector);
//            intent.putExtra("MSG", Constants.PlayerMsg.CONTINUE_MSG);	//继续播放音乐
//            getActivity().startService(intent);
        }

    }

    /**
     * 播放音乐
     */
    public void play() {
        // 开始播放的时候为顺序播放
        play_music_bg.startAnimation(loadingAnimation);
        play_music_bg.setVisibility(View.VISIBLE);
        repeat_none();
        mp3Infos.get(listPosition).setNotPause(true);
//        mService.updatePlayMsg(Constants.PlayerMsg.PLAY_MSG, 0);
//        Intent intent = new Intent(getActivity(), PlayerService.class);
//        intent.putExtra("mp3Infos", mp3Infos);
//        intent.putExtra("listPosition", listPosition);
//        intent.putExtra("MSG", flag);
//        getActivity().startService(intent);
    }

    /**
     * 播放进度改变
     *
     * @param progress
     */
    public void audioTrackChange(int progress) {
        mService.updatePlayMsg(Constants.PlayerMsg.PROGRESS_CHANGE, progress);
//        Intent intent = new Intent(getActivity(), PlayerService.class);
//        intent.putExtra("url", mp3Infos.get(listPosition).getFile_url());
//        intent.putExtra("listPosition", listPosition);
//        intent.putExtra("MSG", Constants.PlayerMsg.PROGRESS_CHANGE);
//        intent.putExtra("progress", progress);
//        getActivity().startService(intent);
    }

    /**
     * 顺序播放
     */
    public void repeat_none() {
        Intent intent = new Intent(Constants.PlayerMsg.CTL_ACTION);
        intent.putExtra("control", 3);
        getActivity().sendBroadcast(intent);
    }

    /**
     * 上一首
     */
    public void previous_music() {
        playBtn.setBackgroundResource(R.drawable.voice_pause_selector);
        listPosition = listPosition - 1;
        if (listPosition >= 0) {
            play_music_bg.startAnimation(loadingAnimation);
            play_music_bg.setVisibility(View.VISIBLE);
            StoryDetailActivity.instance.setCurrMp3(listPosition);
//            Mp3Info mp3Info = mp3Infos.get(listPosition); // 上一首MP3
            mService.setDate(mp3Infos,listPosition);
            mService.updatePlayMsg(Constants.PlayerMsg.PRIVIOUS_MSG, 0);
//            Intent intent = new Intent(getActivity(), PlayerService.class);
//            intent.putExtra("url", mp3Info.getFile_url());
//            intent.putExtra("listPosition", listPosition);
//            intent.putExtra("MSG", Constants.PlayerMsg.PRIVIOUS_MSG);
//            getActivity().startService(intent);
        } else {
            listPosition = 0;
            ToastShow("没有上一首了");
        }
    }

    /**
     * 下一首
     */
    public void next_music() {
        playBtn.setBackgroundResource(R.drawable.voice_pause_selector);
        listPosition = listPosition + 1;
        if (listPosition <= mp3Infos.size() - 1) {
            play_music_bg.startAnimation(loadingAnimation);
            play_music_bg.setVisibility(View.VISIBLE);
            StoryDetailActivity.instance.setCurrMp3(listPosition);
            mService.setDate(mp3Infos, listPosition);
            mService.updatePlayMsg(Constants.PlayerMsg.NEXT_MSG, 0);
//            Mp3Info mp3Info = mp3Infos.get(listPosition);
//            Intent intent = new Intent(getActivity(), PlayerService.class);
//            intent.putExtra("url", mp3Info.getFile_url());
//            intent.putExtra("listPosition", listPosition);
//            intent.putExtra("MSG", Constants.PlayerMsg.NEXT_MSG);
//            getActivity().startService(intent);

        } else {
            listPosition = mp3Infos.size() - 1;
            ToastShow("没有下一首了");
        }
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 控件点击事件
     *
     * @author wwj
     */
    private class ViewOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.play_music:
//                    Intent intent = new Intent(getActivity(), PlayerService.class);
//                    intent.putExtra("mp3Infos", mp3Infos);
//                    intent.putExtra("listPosition", listPosition);
                    if (isPlaying) {
                        StoryDetailActivity.instance.updateItem(false);
                        playBtn.setBackgroundResource(R.drawable.voice_play_selector);
                        mService.updatePlayMsg(Constants.PlayerMsg.PAUSE_MSG, 0);
//                        intent.putExtra("MSG", Constants.PlayerMsg.PAUSE_MSG);
//                        getActivity().startService(intent);
                        isPlaying = false;
                        isPause = true;
                    } else if (isPause) {
                        StoryDetailActivity.instance.updateItem(true);
                        playBtn.setBackgroundResource(R.drawable.voice_pause_selector);
                        mService.updatePlayMsg(Constants.PlayerMsg.CONTINUE_MSG, 0);
//                        intent.putExtra("MSG", Constants.PlayerMsg.CONTINUE_MSG);
//                        getActivity().startService(intent);
                        isPause = false;
                        isPlaying = true;
                    }
//                    StoryDetailActivity.instance.mAdapter.notifyDataSetChanged();
                    break;
                case R.id.previous_music: // 上一首歌曲
                    previous_music();
                    break;
                case R.id.next_music: // 下一首歌曲
                    next_music();
                    break;
            }
        }
    }

    /**
     * 实现监听Seekbar的类
     *
     * @author wwj
     */
    private class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.audioTrack:
                    if (fromUser) {
                        audioTrackChange(progress); // 用户控制进度的改变
                    }
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

    }

    /**
     * 用来接收从service传回来的广播的内部类
     *
     * @author wwj
     */
    public class PlayerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constants.PlayerMsg.MUSIC_CURRENT)) {
                currentTime = intent.getIntExtra("currentTime", -1);
                currentProgress.setText(MediaUtil.formatTime(currentTime));
                music_progressBar.setProgress(currentTime);
            } else if (action.equals(Constants.PlayerMsg.MUSIC_DURATION)) {
                int duration = intent.getIntExtra("duration", -1);
                play_music_bg.clearAnimation();
                play_music_bg.setVisibility(View.GONE);
                music_progressBar.setMax(duration);
                finalProgress.setText(MediaUtil.formatTime(duration));
            } else if (action.equals(Constants.PlayerMsg.UPDATE_ACTION)) {
                // 获取Intent中的current消息，current代表当前正在播放的歌曲
                listPosition = intent.getIntExtra("current", -1);
                if (listPosition == 0) {
                    playBtn.setBackgroundResource(R.drawable.voice_pause_selector);
                    isPause = true;
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (playerReceiver != null) {
            getActivity().unregisterReceiver(playerReceiver);
        }
        unbindService();
//        getActivity().stopService(new Intent(getActivity(),PlayerService.class));
    }
}

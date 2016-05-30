package com.yikang.health.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.yikang.health.constant.Constants;
import com.yikang.health.model.Mp3Info;

import java.util.ArrayList;
import java.util.List;

/***
 * 2013/5/25
 *
 * @author wwj
 *         音乐播放服务
 */
public class PlayerService extends Service {
    private MediaPlayer mediaPlayer; // 媒体播放器对象
//    private int msg;                //播放信息
    private boolean isPause;        // 暂停状态
    private int current = 0;        // 记录当前正在播放的音乐
    private List<Mp3Info> mp3Infos = new ArrayList<>();    //存放Mp3Info对象的集合
    private int status = 3;            //播放状态，默认为顺序播放
    private int currentTime;        //当前播放进度

    /**
     * handler用来接收消息，来发送广播更新播放时间
     */
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                if (mediaPlayer != null) {
                    currentTime = mediaPlayer.getCurrentPosition(); // 获取当前音乐播放的位置
                    Intent intent = new Intent();
                    intent.setAction(Constants.PlayerMsg.MUSIC_CURRENT);
                    intent.putExtra("currentTime", currentTime);
                    sendBroadcast(intent); // 给PlayerActivity发送广播
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            }
        }

        ;
    };
    public class PlayerBinder extends Binder {
        public PlayerService getService() {
            return PlayerService.this;
        }
    }

    private final IBinder mBinder = new PlayerBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("service", "service created");
        mediaPlayer = new MediaPlayer();
        /**
         * 设置音乐播放完成时的监听器
         */
        mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                if (status == 1) { // 单曲循环
                    mediaPlayer.start();
                } else if (status == 2) { // 全部循环
                    current++;
                    if (current > mp3Infos.size() - 1) {    //变为第一首的位置继续播放
                        current = 0;
                    }
                    Intent sendIntent = new Intent(Constants.PlayerMsg.UPDATE_ACTION);
                    sendIntent.putExtra("current", current);
                    // 发送广播，将被Activity组件中的BroadcastReceiver接收到
                    sendBroadcast(sendIntent);
                    play(0);
                } else if (status == 3) { // 顺序播放
                    current++;    //下一首位置
                    if (current <= mp3Infos.size() - 1) {
                        Intent sendIntent = new Intent(Constants.PlayerMsg.UPDATE_ACTION);
                        sendIntent.putExtra("current", current);
                        // 发送广播，将被Activity组件中的BroadcastReceiver接收到
                        sendBroadcast(sendIntent);
                        play(0);
                    } else {
                        mediaPlayer.seekTo(0);
                        current = 0;
                        Intent sendIntent = new Intent(Constants.PlayerMsg.UPDATE_ACTION);
                        sendIntent.putExtra("current", current);
                        // 发送广播，将被Activity组件中的BroadcastReceiver接收到
                        sendBroadcast(sendIntent);
                    }
                } else if (status == 4) {    //随机播放
                    current = getRandomIndex(mp3Infos.size() - 1);
                    System.out.println("currentIndex ->" + current);
                    Intent sendIntent = new Intent(Constants.PlayerMsg.UPDATE_ACTION);
                    sendIntent.putExtra("current", current);
                    // 发送广播，将被Activity组件中的BroadcastReceiver接收到
                    sendBroadcast(sendIntent);
                    play(0);
                }
            }
        });
    }

    /**
     * 获取随机位置
     *
     * @param end
     * @return
     */
    protected int getRandomIndex(int end) {
        int index = (int) (Math.random() * end);
        return index;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    public void setDate(List<Mp3Info> mp3Infos,int listPosition){
        this.mp3Infos = mp3Infos;
        current = listPosition;    //当前播放歌曲的在mp3Infos的位置
    }

    public void updatePlayMsg(int msg,int progress){
        if (msg == Constants.PlayerMsg.PLAY_MSG) {    //直接播放音乐
            play(-1);
        } else if (msg == Constants.PlayerMsg.PAUSE_MSG) {    //暂停
            pause();
        } else if (msg == Constants.PlayerMsg.STOP_MSG) {        //停止
            stop();
        } else if (msg == Constants.PlayerMsg.CONTINUE_MSG) {    //继续播放
            resume();
        } else if (msg == Constants.PlayerMsg.PRIVIOUS_MSG) {    //上一首
            previous();
        } else if (msg == Constants.PlayerMsg.NEXT_MSG) {        //下一首
            next();
        } else if (msg == Constants.PlayerMsg.PROGRESS_CHANGE) {    //进度更新
            currentTime = progress;
            play(currentTime);
        } else if (msg == Constants.PlayerMsg.PLAYING_MSG) {
            handler.sendEmptyMessage(1);
        }
    }

    /**
     *
     * @param currentTime
     */
    private void play(final int currentTime) {
        playThread = new PlayThread(currentTime);
        playThread.start();
    }

    PlayThread playThread;

    class PlayThread extends Thread {
        int currTime;

        public PlayThread(int currentTime) {
            this.currTime = currentTime;

        }

        @Override
        public void run() {
            if (mediaPlayer == null) {
                return;
            }
            try {
                mediaPlayer.reset();// 把各项参数恢复到初始状态
                mediaPlayer.setDataSource(mp3Infos.get(current).getFile_url());
                mediaPlayer.prepare(); // 进行缓冲
                mediaPlayer.setOnPreparedListener(new PreparedListener(currTime));// 注册一个监听器
                handler.sendEmptyMessage(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 暂停音乐
     */
    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPause = true;
        }
    }

    private void resume() {
        if (isPause) {
            mediaPlayer.start();
            isPause = false;
        }
    }

    /**
     * 上一首
     */
    private void previous() {
        Intent sendIntent = new Intent(Constants.PlayerMsg.UPDATE_ACTION);
        sendIntent.putExtra("current", current);
        // 发送广播，将被Activity组件中的BroadcastReceiver接收到
        sendBroadcast(sendIntent);
        play(0);
    }

    /**
     * 下一首
     */
    private void next() {
        Intent sendIntent = new Intent(Constants.PlayerMsg.UPDATE_ACTION);
        sendIntent.putExtra("current", current);
        // 发送广播，将被Activity组件中的BroadcastReceiver接收到
        sendBroadcast(sendIntent);
        play(0);
    }

    /**
     * 停止音乐
     */
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare(); // 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (playThread != null && !playThread.isAlive()) {
            playThread.interrupt();
        }
    }

    /**
     * 实现一个OnPrepareLister接口,当音乐准备好的时候开始播放
     */
    private final class PreparedListener implements OnPreparedListener {
        private int currentTime;

        public PreparedListener(int currentTime) {
            this.currentTime = currentTime;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer.start(); // 开始播放
            if (currentTime > 0) { // 如果音乐不是从头播放
                mediaPlayer.seekTo(currentTime);
            }
            Intent intent = new Intent();
            intent.setAction(Constants.PlayerMsg.MUSIC_DURATION);
            int duration = mediaPlayer.getDuration();
            intent.putExtra("duration", duration);    //通过Intent来传递歌曲的总长度
            sendBroadcast(intent);
        }
    }
}

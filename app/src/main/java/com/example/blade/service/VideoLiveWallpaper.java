package com.example.blade.service;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.IOException;

public class VideoLiveWallpaper extends WallpaperService {


    @Override
    public Engine onCreateEngine() {
        return new VideoEngine();
    }

    private static final String VIDEO_PARAMS_CONTROL_ACTION = "com.example.blade.service.wallpaper";
    private static final String KEY_ACTION = "action";
    private static final int ACTION_VOICE_SILENCE = 110;
    private static final int ACTION_VOICE_NORMAL = 111;
    private static final int ACTION_NICO = 112;


    public static void voiceSilence(Context context) {
        Intent intent = new Intent(VideoLiveWallpaper.VIDEO_PARAMS_CONTROL_ACTION);
        intent.putExtra(VideoLiveWallpaper.KEY_ACTION, VideoLiveWallpaper.ACTION_VOICE_SILENCE);
        context.sendBroadcast(intent);
    }

    public static void voiceNormal(Context context) {
        Intent intent = new Intent(VideoLiveWallpaper.VIDEO_PARAMS_CONTROL_ACTION);
        intent.putExtra(VideoLiveWallpaper.KEY_ACTION, VideoLiveWallpaper.ACTION_VOICE_NORMAL);
        context.sendBroadcast(intent);
    }

// --Commented out by Inspection START (2018/8/30 15:05):
//    public static void voiceNp4(Context context) {
//        Intent intent = new Intent(VideoLiveWallpaper.VIDEO_PARAMS_CONTROL_ACTION);
//        intent.putExtra(VideoLiveWallpaper.KEY_ACTION, VideoLiveWallpaper.ACTION_NICO);
//        context.sendBroadcast(intent);
//    }
// --Commented out by Inspection STOP (2018/8/30 15:05)

    public static void setToWallPaper(Context context) {
        final Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(context, VideoLiveWallpaper.class));
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showLong("");
        }
    }


    class VideoEngine extends Engine {

        private MediaPlayer mMediaPlayer;
        SurfaceHolder Holder;
        String dz;
        private BroadcastReceiver mVideoParamsControlReceiver;

        private void stytoom() {

            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setSurface(Holder.getSurface());
            try {
                SharedPreferences xr2 = getSharedPreferences(getPackageName() + "_preferences", MODE_MULTI_PROCESS);
                dz = xr2.getString("if_b_", "null");
                boolean sy = xr2.getBoolean("ix_", false);
                if ("null".equals(dz)) {
                    ToastUtils.showLong("还没设置视频呀");

                }
                mMediaPlayer.setDataSource(SPUtils.getInstance(getPackageName() + "_preferences").getString("if_b_", "null"));
                mMediaPlayer.setLooping(true);

                if (sy) {
                    mMediaPlayer.setVolume(1.0f, 1.0f);
                } else {
                    mMediaPlayer.setVolume(0, 0);
                }


                mMediaPlayer.prepare();
                mMediaPlayer.start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            //L.d("VideoEngine#onCreate");

            IntentFilter intentFilter = new IntentFilter(VIDEO_PARAMS_CONTROL_ACTION);
            registerReceiver(mVideoParamsControlReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    //L.d("onReceive");
                    int action = intent.getIntExtra(KEY_ACTION, -1);

                    switch (action) {
                        case ACTION_VOICE_NORMAL:
                            mMediaPlayer.setVolume(1.0f, 1.0f);
                            break;
                        case ACTION_VOICE_SILENCE:
                            mMediaPlayer.setVolume(0, 0);
                            break;
                        case ACTION_NICO:

                            break;

                    }
                }
            }, intentFilter);


        }

        @Override
        public void onDestroy() {
            //L.d("VideoEngine#onDestroy");
            unregisterReceiver(mVideoParamsControlReceiver);
            super.onDestroy();


        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            //L.d("VideoEngine#onVisibilityChanged visible = " + visible);
            if (visible) {//如果可见
                mMediaPlayer.start();
                SharedPreferences xr2 = getSharedPreferences(getPackageName() + "_preferences", MODE_MULTI_PROCESS);
                if (!xr2.getString("if_b_", "null").equals(dz)) {
                    //stytoom();
                    //nico.styTool.Toasty.info(getApplicationContext(), "更新设置成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                mMediaPlayer.pause();
            }
        }


        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            //L.d("VideoEngine#onSurfaceCreated ");3
            Holder = holder;
            super.onSurfaceCreated(holder);
            stytoom();
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            // L.d("VideoEngine#onSurfaceChanged ");
            super.onSurfaceChanged(holder, format, width, height);
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            // L.d("VideoEngine#onSurfaceDestroyed ");
            super.onSurfaceDestroyed(holder);
            mMediaPlayer.release();
            mMediaPlayer = null;

        }
    }


}
package com.videoplayer;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.proxy.HttpGetProxy;
import com.proxy.Utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

public class testVideoPlayer extends Activity{
	private final static String TAG="testVideoPlayer";
	static private final int PREBUFFER_SIZE= 8*1024*1024;
	
	private VideoView mVideoView;
	private MediaController mediaController;
	private HttpGetProxy proxy;
	private long startTimeMills;
	
	String YOUKUBiaoQing = "http://v.youku.com/player/getM3U8/vid/126666876/type/flv/ts//v.m3u8";
	String YOUKUGaoQing = "http://v.youku.com/player/getM3U8/vid/126666876/type/mp4/ts//v.m3u8";
	String YOUKUChaoQing = "http://v.youku.com/player/getM3U8/vid/126666876/type/hd2/ts//v.m3u8";
	
//	private String videoUrl ="http://d1.2mp4.net/%E6%89%8B%E6%9C%BA%E7%94%B5%E5%BD%B1/201212/%E5%BF%97%E6%98%8E%E4%B8%8E%E6%98%A5%E5%A8%87-2mp4-800x448.mp4";
//	private String videoUrl ="http://61.182.130.15/vlive.qqvideo.tc.qq.com/s0012wq3ol1.p209.1.mp4?vkey=E203B0F7A4331E51BD81D6F8403C3903701938D17AE44B996AB9155583381561539477B65E427534";
	private String videoUrl = YOUKUBiaoQing;
	private String id=null;
	private long waittime=5000;//等待缓冲时间
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		setTitle("本地HTTP服务-预加载");

		//创建预加载视频文件存放文件夹
		new File( getBufferDir()).mkdirs();
		
		// 初始化VideoView
		mediaController = new MediaController(this);
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		mVideoView.setMediaController(mediaController);
		mVideoView.setOnPreparedListener(mOnPreparedListener);

		// 初始化代理服务器
		proxy = new HttpGetProxy(getBufferDir(),// 预加载视频文件存放路径
				PREBUFFER_SIZE,// 预加载体积
				10);// 预加载文件上限

		Log.e(TAG,"创建代理服务器");
		
		id = System.currentTimeMillis() + "";
		try {
			proxy.startDownload(id, videoUrl, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		delayToStartPlay.sendEmptyMessageDelayed(0, waittime);

		// 一直显示MediaController
		showController.sendEmptyMessageDelayed(0, 1000);
	}
	
	@Override
	public void onStop(){
		super.onStop();
		finish();
		System.exit(0);
	}
	
	private OnPreparedListener mOnPreparedListener=new OnPreparedListener(){

		@Override
		public void onPrepared(MediaPlayer mp) {
			mVideoView.start();
			long duration=System.currentTimeMillis() - startTimeMills;
			Log.e(TAG,"等待缓冲时间:"+waittime+",首次缓冲时间:"+duration);
		}
	};
	
	private Handler delayToStartPlay = new Handler() {
		public void handleMessage(Message msg) {
			startTimeMills=System.currentTimeMillis();
			String proxyUrl = proxy.getLocalURL(id);
			mVideoView.setVideoPath(proxyUrl);
		}
	};
	
	private Handler showController = new Handler() {
		public void handleMessage(Message msg) {
			mediaController.show(0);
		}
	};
	
	public String getBufferDir(){
		String bufferDir = getCacheDir().getPath() + "/ProxyBuffer/files";
//		String bufferDir = Environment.getExternalStorageDirectory()
//		.getAbsolutePath() + "/ProxyBuffer/files";
		return bufferDir;
	}
}
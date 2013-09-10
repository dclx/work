//package com.baidu.bvideoviewsample2;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.Toast;
//
//import com.baidu.tencent_3.R;
//
//public class MainActivity extends Activity implements OnClickListener {
//	private final String TAG = "MainActivity";
//	private Button mPlayBtn1;
//	private Button mPlayBtn2;
//	private Button mPlayBtn3;
//	private Button mPlayBtn4;
//	private Button mPlayBtn5;
//	private Button mPlayBtn6;
//	private CheckBox mCheckBox;
//
//	String TENCENTBiaoQing = "http://111.11.28.149/vlive.qqvideo.tc.qq.com/s0012wq3ol1.p10.1.mp4?br=235&fmt=mp4&platform=9&sdtfrom=v8000&vkey=8CD073164E1C28D4CCF25C300B38ECF6AFB8C5A63FB307379225C2C68D9197392BEAC6EA037072BE&level=3&_appver=1.2.1.6337&_model=MiBOX_iCNTV&_ostype=11&_package=com.tencent.qqlivehd";
//	String TENCENTGaoQing = "http://111.11.28.149/vlive.qqvideo.tc.qq.com/s0012wq3ol1.p10.1.mp4?br=235&fmt=hd&platform=9&sdtfrom=v8000&vkey=8CD073164E1C28D4CCF25C300B38ECF6AFB8C5A63FB307379225C2C68D9197392BEAC6EA037072BE&level=3&_appver=1.2.1.6337&_model=MiBOX_iCNTV&_ostype=11&_package=com.tencent.qqlivehd";
//	String TENCENTChaoQing = "http://111.11.28.149/vlive.qqvideo.tc.qq.com/s0012wq3ol1.p10.1.mp4?br=235&fmt=shd&platform=9&sdtfrom=v8000&vkey=8CD073164E1C28D4CCF25C300B38ECF6AFB8C5A63FB307379225C2C68D9197392BEAC6EA037072BE&level=3&_appver=1.2.1.6337&_model=MiBOX_iCNTV&_ostype=11&_package=com.tencent.qqlivehd";
//
//	String TENCENTM3U8BiaoQing = "http://api.wanhuatong.tv/media/play/teleplay/1NtxySyZ9j5/sd/v.m3u8";
//	String TENCENTM3U8GaoQing = "http://api.wanhuatong.tv/media/play/teleplay/1NtxySyZ9j5/hd/v.m3u8";
//	String TENCENTM3U8ChaoQing = "http://api.wanhuatong.tv/media/play/teleplay/1NtxySyZ9j5/shd/v.m3u8";
//	
//	String source = TENCENTBiaoQing;
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//		initUI();
//	}
//	
//	void initUI(){
//		mPlayBtn1 = (Button)findViewById(R.id.playBtn1);
//		mPlayBtn2 = (Button)findViewById(R.id.playBtn2);
//		mPlayBtn3 = (Button)findViewById(R.id.playBtn3);
//		mPlayBtn4 = (Button)findViewById(R.id.playBtn4);
//		mPlayBtn5 = (Button)findViewById(R.id.playBtn5);
//		mPlayBtn6 = (Button)findViewById(R.id.playBtn6);
//		mCheckBox = (CheckBox)findViewById(R.id.isHard);
//		
//		mPlayBtn1.setOnClickListener(this);
//		mPlayBtn2.setOnClickListener(this);
//		mPlayBtn3.setOnClickListener(this);
//		mPlayBtn4.setOnClickListener(this);
//		mPlayBtn5.setOnClickListener(this);
//		mPlayBtn6.setOnClickListener(this);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		int id = v.getId();
//		switch(id){
//		case R.id.playBtn1:
//			source = TENCENTBiaoQing;
//			playVideo();
//			break;
//		case R.id.playBtn2:
//			source = TENCENTGaoQing;
//			playVideo();
//			break;
//		case R.id.playBtn3:
//			source = TENCENTChaoQing;
//			playVideo();
//			break;
//		case R.id.playBtn4:
//			source = TENCENTM3U8BiaoQing;
//			playVideo();
//			break;
//		case R.id.playBtn5:
//			source = TENCENTM3U8GaoQing;
//			playVideo();
//			break;
//		case R.id.playBtn6:
//			source = TENCENTM3U8ChaoQing;
//			playVideo();
//			break;
//		default:
//		}
//	}
//	
//	private void playVideo(){
//		if(source == null || source.equals("")){
//			/**
//			 */
//			Toast.makeText(this, "please input your video source", 500).show();
//		}else{
//			Intent intent = new Intent(this, VideoViewPlayingActivity.class);
//			intent.setData(Uri.parse(source));
//			intent.putExtra("isHW", mCheckBox.isChecked());
//			startActivity(intent);
//		}	
//	}
//}

package com.baidu.bvideoviewsample2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.baidu.tencent_3.R;

public class MainActivity extends Activity implements OnClickListener {
	
	private final String TAG = "MainActivity";
	private Button mPlayBtn1;
	private Button mPlayBtn2;
	private Button mPlayBtn3;
	private Button mPlayBtn4;
	private Button mPlayBtn5;
	private Button mPlayBtn6;
	private Button mPlayBtn7;
	private Button mPlayBtn8;
	private Button mPlayBtn9;
	private Button mPlayBtn10;
	private CheckBox mCheckBox;
	
	String BD1 = "http://www.baidupcs.com/file/351f85e5347fc44dd56d7ca7d2913879?xcode=52970fea9921c73eeb36499be19fedc3ee7656f78344ee77&fid=2399527220-250528-1182288541&time=1378551473&sign=FDTAXER-DCb740ccc5511e5e8fedcff06b081203-xeOunglzUvoidv82dHCZ4TEz3Ms%3D&to=wb&fm=N,B,G&expires=8h&rt=sh&r=573572088&logid=1425762566&sh=1&fn=%5B%E8%BF%85%E9%9B%B7%E4%B8%8B%E8%BD%BDwww.2tu.cc%5D%E4%B8%8D%E4%BA%8C%E7%A5%9E%E6%8E%A2.BD1280%E8%B6%85%E6%B8%85%E5%9B%BD%E7%B2%A4%E5%8F%8C%E8%AF%AD%E4%B8%AD%E5%AD%97.mkv";
	String BD2 = "http://120.192.87.81/cdn.baidupcs.com/file/a70441a808d00e0465a638ba49792f06?xcode=47c80a0740e93e0bddb7ae91536f6c3fc2561ad2f4725acb&fid=2399527220-250528-1937753898&time=1378555221&sign=FDTAXER-DCb740ccc5511e5e8fedcff06b081203-KCx5iH6gV8UFpxnySSvZ0Cg%2FiTY%3D&to=cb&fm=N,B,G&expires=8h&rt=sh&r=107906314&logid=1413882518&sh=1&fn=%5B%E5%A4%A9%E6%9C%BA%C2%B7%E5%AF%8C%E6%98%A5%E5%B1%B1%E5%B1%85%E5%9B%BE%20%E8%93%9D%E5%85%891280%E5%9B%BD%E8%AF%AD%E5%AE%8C%E7%BE%8E%E4%B8%AD%E5%AD%97%5D%5B%E6%9C%80%E6%96%B0%E5%BD%B1%E8%A7%86zx132.com%5D.rmvb&wsiphost=ipdbm";
	String BD3 = "http://183.221.245.34/cdn.baidupcs.com/file/55e57914452106b9ed8512950674c84a?xcode=0ffb389e39f805b1ed63a5ae58a1c045e1291591a640d02c&fid=2869789857-250528-4181510945&time=1378555295&sign=FDTAXER-DCb740ccc5511e5e8fedcff06b081203-%2BUcgfKU5CW%2FCo%2F%2FbEMmbKbzCnn4%3D&to=cb&fm=N,B,G&expires=8h&rt=sh&r=985457734&logid=285586646&sh=1&fn=%E9%92%A2%E9%93%81%E4%BE%A03%28%E5%88%9D%E7%89%88%29.Iron.Man.3.2013.WEB-MP4-%E4%BA%BA%E4%BA%BA%E5%BD%B1%E8%A7%86%E5%8E%9F%E5%88%9B%E7%BF%BB%E8%AF%91%E4%B8%AD%E8%8B%B1%E5%8F%8C%E8%AF%AD%E5%AD%97%E5%B9%95.mp4&wsiphost=ipdbm";
	String BD4 = "http://hot.cdn.baidupcs.com/file/382618458fa3469fd526be03c3da29b7?xcode=3e1686971b09f1c5416b5f0f10936e1fa75230b2c8436cdc&fid=2399527220-250528-2401226747&time=1378555158&sign=FDTAXER-DCb740ccc5511e5e8fedcff06b081203-OYnXW7L%2B6fSJ%2FqIgQ%2FDGRJpOLks%3D&to=hc&fm=N,B,G&expires=8h&rt=sh&r=769449593&logid=23456265&sh=1&fn=%E6%BD%AE%E6%80%A7%E5%8A%9E%E5%85%AC%E5%AE%A4.flv";

	String ZB1 = "http://tvindexlive02.is.ysten.com:8080/ysten-business/live/cctv-3/all.m3u8";
	String ZB2 = "http://tvindexlive02.is.ysten.com:8080/ysten-business/live/cctv-5/all.m3u8";
	String ZB3 = "http://tvindexlive02.is.ysten.com:8080/ysten-business/live/fhzixun/all.m3u8";
	String ZB4 = "http://182.138.31.18:8199/HNTVHD";
	String ZB5 = "http://lv3.livem3u8.na.itv.cn/live/7c085f14b201495f9b8d27a2f5215076.m3u8?bitrate=800";
	String ZB6 = "http://lv3.livem3u8.na.itv.cn/live/4a898f8d4d8140e589ec6da1f126c6f0.m3u8?bitrate=800";
	
	String source = BD1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
	}
	
	void initUI(){
		mPlayBtn1 = (Button)findViewById(R.id.playBtn1);
		mPlayBtn2 = (Button)findViewById(R.id.playBtn2);
		mPlayBtn3 = (Button)findViewById(R.id.playBtn3);
		mPlayBtn4 = (Button)findViewById(R.id.playBtn4);
		mPlayBtn5 = (Button)findViewById(R.id.playBtn5);
		mPlayBtn6 = (Button)findViewById(R.id.playBtn6);
		mPlayBtn7 = (Button)findViewById(R.id.playBtn7);
		mPlayBtn8 = (Button)findViewById(R.id.playBtn8);
		mPlayBtn9 = (Button)findViewById(R.id.playBtn9);
		mPlayBtn10 = (Button)findViewById(R.id.playBtn10);
		mCheckBox = (CheckBox)findViewById(R.id.isHard);
		
		mPlayBtn1.setOnClickListener(this);
		mPlayBtn2.setOnClickListener(this);
		mPlayBtn3.setOnClickListener(this);
		mPlayBtn4.setOnClickListener(this);
		mPlayBtn5.setOnClickListener(this);
		mPlayBtn6.setOnClickListener(this);
		mPlayBtn7.setOnClickListener(this);
		mPlayBtn8.setOnClickListener(this);
		mPlayBtn9.setOnClickListener(this);
		mPlayBtn10.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch(id){
		case R.id.playBtn1:
			source = BD1;
			playVideo();
			break;
		case R.id.playBtn2:
			source = BD2;
			playVideo();
			break;
		case R.id.playBtn3:
			source = BD3;
			playVideo();
			break;
		case R.id.playBtn4:
			source = BD4;
			playVideo();
			break;
		case R.id.playBtn5:
			source = ZB1;
			playVideo();
			break;
		case R.id.playBtn6:
			source = ZB2;
			playVideo();
			break;
		case R.id.playBtn7:
			source = ZB3;
			playVideo();
			break;
		case R.id.playBtn8:
			source = ZB4;
			playVideo();
			break;
		case R.id.playBtn9:
			source = ZB5;
			playVideo();
			break;
		case R.id.playBtn10:
			source = ZB6;
			playVideo();
			break;
		default:
		}
	}
	
	private void playVideo(){
		if(source == null || source.equals("")){
			/**
			 */
			Toast.makeText(this, "please input your video source", 500).show();
		}else{
			Intent intent = new Intent(this, VideoViewPlayingActivity.class);
			intent.setData(Uri.parse(source));
			intent.putExtra("isHW", mCheckBox.isChecked());
			startActivity(intent);
		}	
	}
}

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

import com.baidu.tencent_4.R;

public class MainActivity extends Activity implements OnClickListener {
	private final String TAG = "MainActivity";
	private Button mPlayBtn1;
	private Button mPlayBtn2;
	private Button mPlayBtn3;
	private CheckBox mCheckBox;

	String TENCENTBiaoQing = "http://111.11.28.149/vlive.qqvideo.tc.qq.com/s0012wq3ol1.p10.1.mp4?br=235&fmt=mp4&platform=9&sdtfrom=v8000&vkey=8CD073164E1C28D4CCF25C300B38ECF6AFB8C5A63FB307379225C2C68D9197392BEAC6EA037072BE&level=3&_appver=1.2.1.6337&_model=MiBOX_iCNTV&_ostype=11&_package=com.tencent.qqlivehd";
	String TENCENTGaoQing = "http://111.11.28.149/vlive.qqvideo.tc.qq.com/s0012wq3ol1.p10.1.mp4?br=235&fmt=hd&platform=9&sdtfrom=v8000&vkey=8CD073164E1C28D4CCF25C300B38ECF6AFB8C5A63FB307379225C2C68D9197392BEAC6EA037072BE&level=3&_appver=1.2.1.6337&_model=MiBOX_iCNTV&_ostype=11&_package=com.tencent.qqlivehd";
	String TENCENTChaoQing = "http://111.11.28.149/vlive.qqvideo.tc.qq.com/s0012wq3ol1.p10.1.mp4?br=235&fmt=shd&platform=9&sdtfrom=v8000&vkey=8CD073164E1C28D4CCF25C300B38ECF6AFB8C5A63FB307379225C2C68D9197392BEAC6EA037072BE&level=3&_appver=1.2.1.6337&_model=MiBOX_iCNTV&_ostype=11&_package=com.tencent.qqlivehd";
	
	String source = TENCENTBiaoQing;
	
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
		mCheckBox = (CheckBox)findViewById(R.id.isHard);
		
		mPlayBtn1.setOnClickListener(this);
		mPlayBtn2.setOnClickListener(this);
		mPlayBtn3.setOnClickListener(this);
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
			source = TENCENTBiaoQing;
			playVideo();
			break;
		case R.id.playBtn2:
			source = TENCENTGaoQing;
			playVideo();
			break;
		case R.id.playBtn3:
			source = TENCENTChaoQing;
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
